package com.mediadriver.atlas.java.inspect.v2;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediadriver.atlas.java.v2.JavaClass;
import com.mediadriver.atlas.java.v2.JavaEnumField;
import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.java.v2.AtlasJavaModelFactory;
import com.mediadriver.atlas.v2.AtlasModelFactory;
import com.mediadriver.atlas.v2.FieldStatus;
import com.mediadriver.atlas.v2.FieldType;

public class ClassInspector implements Serializable {
	
	private static final long serialVersionUID = 6634950157813704038L;
	private static final Logger logger = LoggerFactory.getLogger(ClassInspector.class);
	public static final int MAX_REENTRY_LIMIT = 1;
	public static final int MAX_ARRAY_DIM_LIMIT = 256; // JVM specification limit

	public static final Set<String> primitiveClasses = new HashSet<String>(Arrays.asList("byte", "short", "int", "long", "float", "double", "boolean", "char"));
	public static final Set<String> boxedPrimitiveClasses = new HashSet<String>(Arrays.asList("java.lang.Byte", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double", "java.lang.Boolean", "java.lang.Character", "java.lang.String"));
	
	public static Map<String, JavaClass> inspectPackage(String packageName) throws ClassNotFoundException, InspectionException {
		return inspectPackages(Arrays.asList(packageName), false);
	}
	
	public static Map<String, JavaClass> inspectPackages(String packageName, boolean inspectChildren) throws ClassNotFoundException, InspectionException {
		return inspectPackages(Arrays.asList(packageName), inspectChildren);
	}
	
	public static Map<String, JavaClass> inspectPackages(List<String> packages, boolean inspectChildren) throws ClassNotFoundException, InspectionException {
		packages = inspectChildren ? findChildPackages(packages) : packages;
		Map<String, JavaClass> classes = new HashMap<>();
		for (String p : packages) {
			classes.putAll(inspectClasses(findClassesForPackage(p)));
		}		
		return classes;
	}
	
	public static List<String> findClassesForPackage(String packageName) {		
		List<String> classNames = new LinkedList<>();
		List<Class<?>> classes = ClassFinder.find(packageName);
		if (classes != null) {
			for (Class<?> clz : classes) {
				classNames.add(clz.getCanonicalName());
			}
		}
		return classNames;
	}
	
	public static List<String> findChildPackages(List<String> packages) {
		List<String> foundPackages = new LinkedList<>();
		for (String p : packages) {
			foundPackages.addAll(findChildPackages(p));
		}
		return foundPackages;
	}
	
	public static List<String> findChildPackages(String packageName) {
		List<String> packageNames = new LinkedList<>();
		Package originalPackage = Package.getPackage(packageName);
		Package[] allPackages = Package.getPackages();
		if (allPackages != null) {
			for (Package tmpPackage : allPackages) {
				if (tmpPackage.getName().startsWith(originalPackage.getName())) {
					packageNames.add(tmpPackage.getName());
				}
			}
		}
		return packageNames;
	}		
	
	public static Map<String,JavaClass> inspectClasses(List<String> classNames) {
		Map<String,JavaClass> classes = new HashMap<>();
		for (String c : classNames) {
			JavaClass d = inspectClass(c);
			classes.put(d.getFullyQualifiedName(), d);
		}
		return classes;
	}

	public static JavaClass inspectClass(String className) {
		JavaClass d = null;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
			d = inspectClass(clazz);
		} catch (ClassNotFoundException cnfe) {
			d = AtlasJavaModelFactory.createJavaClass();
			d.setFullyQualifiedName(className);
			d.setStatus(FieldStatus.NOT_FOUND);
		}
		return d;
	}
	
	public static JavaClass inspectClass(Class<?> clazz) {
		JavaClass javaClass = AtlasJavaModelFactory.createJavaClass();
		inspectClass(clazz, javaClass, new HashSet<String>());
		return javaClass;
	}
		
	protected static void inspectClass(Class<?> clazz, JavaClass javaClass, Set<String> cachedClasses) {

		Class<?> clz = clazz;
		if(clazz.isArray()) {
			javaClass.setArray(clazz.isArray());
			javaClass.setArrayDimensions(detectArrayDimensions(clazz));
			clz = detectArrayClass(clazz);
		} else {
			if(javaClass.isArray() == null) {
				javaClass.setArray(clazz.isArray());
			}
			clz = clazz;
		}
		
		javaClass.setClassName(clz.getSimpleName());
		javaClass.setPackageName((clz.getPackage() != null ? clz.getPackage().getName() : null));
		javaClass.setFullyQualifiedName(clz.getCanonicalName());	
		javaClass.setAnnotation(clz.isAnnotation());
		javaClass.setAnnonymous(clz.isAnonymousClass());
		javaClass.setEnumeration(clz.isEnum());
		javaClass.setInterface(clz.isInterface());
		javaClass.setLocalClass(clz.isLocalClass());
		javaClass.setMemberClass(clz.isMemberClass());
		javaClass.setPrimitive(clz.isPrimitive());
		javaClass.setSynthetic(clz.isSynthetic());
			
		Field[] fields = clz.getDeclaredFields();
		if (fields != null) {
			for (Field f : fields) {
				if (Enum.class.isAssignableFrom(f.getType())) {
					continue;
				}
				JavaField s = inspectField(f, cachedClasses);
				javaClass.getJavaFields().getJavaField().add(s);
			}
		}
		
		Object[] enumConstants = clz.getEnumConstants();
		if (enumConstants != null) {
			javaClass.setEnumeration(true);
			for (Object o : enumConstants) {
				JavaEnumField out = new JavaEnumField();
				if (o instanceof Enum) {
					Enum<?> in = (Enum<?>) o;
					out.setName(in.name());
					out.setOrdinal(in.ordinal());
					javaClass.getJavaEnumFields().getJavaEnumField().add(out);
					out.setStatus(FieldStatus.SUPPORTED);
				} else {
					out.setClassName(o.getClass().getCanonicalName());
					out.setStatus(FieldStatus.ERROR);
				}
			}
		} else {
			javaClass.setEnumeration(false);
		}
		
		Method[] methods = clz.getDeclaredMethods();
		if (methods != null) {
			for (Method m : methods) {
				JavaField s = new JavaField();
				s.setName(m.getName());
				s.setSynthetic(m.isSynthetic());
				
				if(m.isVarArgs() || m.isBridge() || m.isSynthetic() || m.isDefault()) {
					s.setStatus(FieldStatus.UNSUPPORTED);
					logger.warn("VarArg, Bridge, Synthetic or Default method " + m.getName() + " detected");
					continue;
				} else {
					s.setSynthetic(m.isSynthetic());
				}
							
				if(m.getName().startsWith("get") || m.getName().startsWith("is")) {
					s = inspectGetMethod(m, s, cachedClasses);
				}

				if(m.getName().startsWith("set")) {
					s = inspectSetMethod(m, s, cachedClasses);
				}
				
				boolean found = false;
				for(int i=0; i < javaClass.getJavaFields().getJavaField().size(); i++) {
					JavaField exists = (JavaField)javaClass.getJavaFields().getJavaField().get(i);
					if(s.getName().equals(exists.getName())) {
						found = true;
						
						// Merge get/set method info for interfaces that don't have fields
						if(exists.getGetMethod() == null && s.getGetMethod() != null) {
							exists.setGetMethod(s.getGetMethod());
						}
						if(exists.getSetMethod() == null && s.getSetMethod() != null) {
							exists.setSetMethod(s.getSetMethod());
						}
					}
				}
			
				if(found) {
					if(logger.isDebugEnabled()) {
						logger.debug("Field already defined for method: " + m.getName() + " class: " + clz.getName());
					}
				} else if(s.getGetMethod() != null || s.getSetMethod() != null) {
						javaClass.getJavaFields().getJavaField().add(s);
				} else {
					if(logger.isDebugEnabled()) {
						logger.debug("Ignoring non-field method: " + m.getName() + " class: " + clz.getName());
					}
				}
		
			}
		}
		
		//TODO: annotations, generics, enums, class modifiers (public, synchronized, etc), 
		//more of these here: https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html#isPrimitive--
		//TODO: exceptions
		//TODO: lists
		//return javaClass;
	}
	
	protected static JavaField inspectGetMethod(Method m, JavaField s, Set<String> cachedClasses) {
		
		s.setName(StringUtil.removeGetterAndLowercaseFirstLetter(m.getName()));

		if(m.getParameterCount() != 0) {
			s.setStatus(FieldStatus.UNSUPPORTED);;
			return s;
		}
		
		if(m.getReturnType().equals(Void.TYPE)) {
			s.setStatus(FieldStatus.UNSUPPORTED);
			return s;
		}
		
		Class<?> returnType = m.getReturnType();
		if(returnType.isArray()) {
			s.setArray(true);
			s.setArrayDimensions(detectArrayDimensions(returnType));
			returnType = detectArrayClass(returnType);
		} else {
			s.setArray(false);
		}
		
		s.setClassName(returnType.getCanonicalName());
		s.setGetMethod(m.getName());
		if(isFieldPrimitive(returnType.getCanonicalName())) {	
			s.setPrimitive(true);
			s.setType(AtlasModelFactory.fieldTypeFromClassName(returnType.getCanonicalName()));
			s.setStatus(FieldStatus.SUPPORTED);
		} else if(isFieldBoxedPrimitive(returnType.getCanonicalName())) {
			s.setPrimitive(true);
			s.setType(AtlasModelFactory.fieldTypeFromClassName(returnType.getCanonicalName()));
			s.setStatus(FieldStatus.SUPPORTED);
		} else {
			s.setPrimitive(false);
			s.setType(FieldType.COMPLEX);
			
			Class<?> complexClazz = null;
			JavaClass tmpField = convertJavaFieldToJavaClass(s);
			s = tmpField;
			
			if(returnType.getCanonicalName() == null) {
				s.setStatus(FieldStatus.UNSUPPORTED);
			} else if(!cachedClasses.contains(returnType.getCanonicalName())) {
				try {
					complexClazz = Class.forName(returnType.getCanonicalName());
					cachedClasses.add(returnType.getCanonicalName());
					inspectClass(complexClazz, tmpField, cachedClasses);
					if(tmpField.getStatus() == null) {
						s.setStatus(FieldStatus.SUPPORTED);
					}
				} catch (ClassNotFoundException cnfe) {
					s.setStatus(FieldStatus.NOT_FOUND);
				}
			} else {
				s.setStatus(FieldStatus.CACHED);
			}
		}
		
		return s;
	}
	
	protected static JavaField inspectSetMethod(Method m, JavaField s, Set<String> cachedClasses) {

		s.setName(StringUtil.removeSetterAndLowercaseFirstLetter(m.getName()));

		if(m.getParameterCount() != 1) {
			s.setStatus(FieldStatus.UNSUPPORTED);;
			return s;
		}
		
		if(!m.getReturnType().equals(Void.TYPE)) {
			s.setStatus(FieldStatus.UNSUPPORTED);
			return s;
		}
		
		Class<?>[] params = m.getParameterTypes();
		if(params == null || params.length != 1) {
			s.setStatus(FieldStatus.UNSUPPORTED);
			return s;
		}
		
		Class<?> paramType = params[0];				
		if(paramType.isArray()) {
			s.setArray(true);
			s.setArrayDimensions(detectArrayDimensions(paramType));
			paramType = detectArrayClass(paramType);
		} else {
			s.setArray(false);
		}
		
		s.setClassName(paramType.getCanonicalName());
		s.setSetMethod(m.getName());
		if(isFieldPrimitive(paramType.getCanonicalName())) {	
			s.setPrimitive(true);
			s.setType(AtlasModelFactory.fieldTypeFromClassName(paramType.getCanonicalName()));
			s.setStatus(FieldStatus.SUPPORTED);
		} else if(isFieldBoxedPrimitive(paramType.getCanonicalName())) {
			s.setPrimitive(true);
			s.setType(AtlasModelFactory.fieldTypeFromClassName(paramType.getCanonicalName()));
			s.setStatus(FieldStatus.SUPPORTED);
		} else {
			s.setPrimitive(false);
			s.setType(FieldType.COMPLEX);

			Class<?> complexClazz = null;
			JavaClass tmpField = convertJavaFieldToJavaClass(s);
			s = tmpField;
			
			if(paramType.getCanonicalName() == null) {
				s.setStatus(FieldStatus.UNSUPPORTED);
			} else if(!cachedClasses.contains(paramType.getCanonicalName())) {
				try {
					complexClazz = Class.forName(paramType.getCanonicalName());
					cachedClasses.add(paramType.getCanonicalName());
					inspectClass(complexClazz, tmpField, cachedClasses);
					if(tmpField.getStatus() == null) {
						s.setStatus(FieldStatus.SUPPORTED);
					}
				} catch (ClassNotFoundException cnfe) {
					s.setStatus(FieldStatus.NOT_FOUND);
				}
			} else {
				s.setStatus(FieldStatus.CACHED);
			}
		}
		
		return s;
	}
	
	protected static JavaField inspectField(Field f, Set<String> cachedClasses) {

		JavaField s = new JavaField();
		Class<?> clazz = f.getType();
		
		if(clazz.isArray()) {
			s.setArray(true);
			s.setArrayDimensions(detectArrayDimensions(clazz));
			clazz = detectArrayClass(clazz);
		} else {
			s.setArray(false);
		}
		
		s.setName(f.getName());
		
		if(isFieldPrimitive(clazz.getCanonicalName())) {
			s.setPrimitive(true);
			s.setType(AtlasModelFactory.fieldTypeFromClassName(clazz.getCanonicalName()));
			s.setStatus(FieldStatus.SUPPORTED);
		} else if(isFieldBoxedPrimitive(clazz.getCanonicalName())) {
			s.setPrimitive(true);
			s.setType(AtlasModelFactory.fieldTypeFromClassName(clazz.getCanonicalName()));
			s.setStatus(FieldStatus.SUPPORTED);
		} else {
			s.setPrimitive(false);
			s.setType(FieldType.COMPLEX);
			
			Class<?> complexClazz = null;
			JavaClass tmpField = convertJavaFieldToJavaClass(s);
			s = tmpField;
			
			if(clazz.getCanonicalName() == null) {
				s.setStatus(FieldStatus.UNSUPPORTED);
			} else if(!cachedClasses.contains(clazz.getCanonicalName())) {
				try {
					complexClazz = Class.forName(clazz.getCanonicalName());
					cachedClasses.add(clazz.getCanonicalName());
					inspectClass(complexClazz, tmpField, cachedClasses);
					if(tmpField.getStatus() == null) {
						s.setStatus(FieldStatus.SUPPORTED);
					}
				} catch (ClassNotFoundException cnfe) {
					s.setStatus(FieldStatus.NOT_FOUND);
				}
			} else {
				s.setStatus(FieldStatus.CACHED);
			}
		}	
	
		s.setClassName(clazz.getCanonicalName());
		s.setSynthetic(f.isSynthetic());
		
		Annotation[] annotations = f.getAnnotations();
		if (annotations != null) {
			for (Annotation a : annotations) {
				s.getAnnotations().getString().add(a.annotationType().getCanonicalName());
			}
		}
		
		try {
			String getterName = "get" + StringUtil.capitalizeFirstLetter(f.getName());			
			f.getDeclaringClass().getMethod(getterName);
			s.setGetMethod(getterName);
		} catch (NoSuchMethodException e) { 
			if(logger.isDebugEnabled()) {
				logger.debug("No 'get' method for field named: " + f.getName() + " in class: " + f.getDeclaringClass().getName());
			}
		}
		if (s.getGetMethod() == null && ("boolean".equals(s.getClassName()) || "java.lang.Boolean".equals(s.getClassName()))) {
			try {
				String getterName = "is" + StringUtil.capitalizeFirstLetter(f.getName());			
				f.getDeclaringClass().getMethod(getterName);
				s.setGetMethod(getterName);
			} catch (NoSuchMethodException e) {
				if(logger.isDebugEnabled()) {
					logger.debug("No 'is' method for field named: " + f.getName() + " in class: " + f.getDeclaringClass().getName());
				}
			}
		}		
		try {
			String setterName = "set" + StringUtil.capitalizeFirstLetter(f.getName());
			f.getDeclaringClass().getMethod(setterName, clazz);
			s.setSetMethod(setterName);
		} catch (NoSuchMethodException e) {
			if(logger.isDebugEnabled()) {
				logger.debug("No 'set' method for field named: " + f.getName() + " in class: " + f.getDeclaringClass().getName());
			}
		}
		return s;
	}
	
	protected static boolean isFieldPrimitive(String fieldType) {
		return primitiveClasses.contains(fieldType); 
	}
	
	protected static boolean isFieldBoxedPrimitive(String fieldType) {
		return boxedPrimitiveClasses.contains(fieldType); 
	}
	
	protected static Integer detectArrayDimensions(Class<?> clazz) {
		Integer arrayDim = new Integer(0);
		if(clazz == null) {
			return null;
		}	
		
		if(!clazz.isArray()) {
			return arrayDim;
		} else { 
			arrayDim++;
		}
		
		Class<?> tmpClazz = clazz.getComponentType();
		while(tmpClazz != null && tmpClazz.isArray() && arrayDim < MAX_ARRAY_DIM_LIMIT) {
			arrayDim++;
			tmpClazz = tmpClazz.getComponentType();
		}  
		return arrayDim;
	}
	
	protected static Class<?> detectArrayClass(Class<?> clazz) {
		Integer arrayDim = new Integer(0);
		if(clazz == null) {
			return null;
		}	
		
		if(!clazz.isArray()) {
			return clazz;
		} else { 
			arrayDim++;
		}
		
		Class<?> tmpClazz = clazz.getComponentType();
		while(tmpClazz != null && tmpClazz.isArray() && arrayDim < MAX_ARRAY_DIM_LIMIT) {
			arrayDim++;
			tmpClazz = tmpClazz.getComponentType();
		}  
		return tmpClazz;
	}
	
	protected static JavaClass convertJavaFieldToJavaClass(JavaField javaField) {
		JavaClass javaClass = AtlasJavaModelFactory.createJavaClass();
		javaClass.setArray(javaField.isArray());
		javaClass.setArrayDimensions(javaField.getArrayDimensions());
		javaClass.setCollection(javaField.isCollection());
		javaClass.setPrimitive(javaField.isPrimitive());
		javaClass.setSynthetic(javaField.isSynthetic());
		javaClass.setClassName(javaField.getClassName());
		javaClass.setGetMethod(javaField.getGetMethod());
		javaClass.setName(javaField.getName());
		javaClass.setSetMethod(javaField.getSetMethod());
		javaClass.setStatus(javaField.getStatus());
		javaClass.setType(javaField.getType());
		javaClass.setValue(javaField.getValue());
		return javaClass;
	}
}
