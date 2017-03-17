package com.mediadriver.atlas.java.module.v2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.mediadriver.atlas.api.v2.AtlasException;
import com.mediadriver.atlas.api.v2.AtlasNotFoundException;
import com.mediadriver.atlas.api.v2.AtlasSession;
import com.mediadriver.atlas.core.v2.AtlasUtil;
import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.javapath.v2.JavaPath;
import com.mediadriver.atlas.spi.v2.AtlasModule;
import com.mediadriver.atlas.spi.v2.AtlasModuleDetail;
import com.mediadriver.atlas.spi.v2.AtlasModuleMode;
import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.v2.AtlasModelFactory;
import com.mediadriver.atlas.v2.Field;
import com.mediadriver.atlas.v2.FieldAction;
import com.mediadriver.atlas.v2.FieldActions;
import com.mediadriver.atlas.v2.FieldMapping;
import com.mediadriver.atlas.v2.FieldMappings;
import com.mediadriver.atlas.v2.MapAction;
import com.mediadriver.atlas.v2.MapFieldMapping;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.v2.SeparateFieldMapping;

@AtlasModuleDetail(name="JavaModule", uri="atlas:java", modes={"SOURCE", "TARGET"}, dataFormats={"java"}, configPackages={"com.mediadriver.atlas.java.v2"})
public class JavaModule implements AtlasModule {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processInput(AtlasSession session) throws AtlasException {
		AtlasMapping atlasMapping = session.getAtlasMapping();
		FieldMappings fieldMappings = atlasMapping.getFieldMappings();
		List<FieldMapping> fieldMappingList = fieldMappings.getFieldMapping();
		Object source = session.getInput();
		try { 
			MappedField sourceMappedField = null;
			Field sourceField = null;
			for(FieldMapping fieldMapping : fieldMappingList) {
				if(fieldMapping instanceof MapFieldMapping) {
					sourceMappedField = ((MapFieldMapping)fieldMapping).getInputField();
					sourceField = sourceMappedField.getField();
					if(sourceField instanceof JavaField) {
						populateValue((JavaField)sourceField, source);
					}
				} else if(fieldMapping instanceof SeparateFieldMapping) {
					sourceMappedField = ((SeparateFieldMapping)fieldMapping).getInputField();
					sourceField = sourceMappedField.getField();
					if(sourceField instanceof JavaField) {
						populateValue((JavaField)sourceField, source);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			throw new AtlasNotFoundException(e.getMessage(), e.getCause());
		} catch (Exception e) {
			throw new AtlasException(e.getMessage(), e.getCause());
		}
		
	}
	
	protected void populateValue(JavaField javaField, Object source) throws Exception {
		Object sourceValue = null;
		if(javaField.getPath().contains(JavaPath.JAVAPATH_SEPARATOR)) {
			Object parentObject = getParentObject(new JavaPath(javaField.getPath()), source);
			Method parentGet = parentObject.getClass().getDeclaredMethod(javaField.getGetMethod());
			sourceValue = parentGet.invoke(parentObject);
			javaField.setValue(sourceValue);
		} else {
			Method getText = source.getClass().getMethod(javaField.getGetMethod());
			javaField.setValue(getText.invoke(source));
		}
	}

	@Override
	public void processOutput(AtlasSession session) throws AtlasException {
		String targetClassName = AtlasUtil.getUriParameterValue(session.getAtlasMapping().getTargetUri(), "className");
		AtlasMapping atlasMapping = session.getAtlasMapping();
		FieldMappings fieldMappings = atlasMapping.getFieldMappings();
		List<FieldMapping> fieldMappingList = fieldMappings.getFieldMapping();
		
		try {
			Class<?> targetClazz = this.getClass().getClassLoader().loadClass(targetClassName);
			Object targetObject = targetClazz.newInstance();
			
			MappedField sourceMappedField = null;
			MappedField targetMappedField = null;
			Field sourceField = null;
			Field targetField = null;
			Object sourceValue = null;
			
			for(FieldMapping fieldMapping : fieldMappingList) {
				if(fieldMapping instanceof MapFieldMapping) {
					sourceMappedField = ((MapFieldMapping)fieldMapping).getInputField();
					targetMappedField = ((MapFieldMapping)fieldMapping).getOutputField();
					sourceField = sourceMappedField.getField();
					sourceValue = sourceField.getValue();
					
					targetField = targetMappedField.getField();
					if(targetField instanceof JavaField) {
						JavaField jtf = (JavaField)targetField;
					
						Method targetMethod = targetClazz.getDeclaredMethod(jtf.getSetMethod(), AtlasModelFactory.classFromFieldType(jtf.getType()));
						targetMethod.invoke(targetObject, sourceValue);
					}				
				} else if(fieldMapping instanceof SeparateFieldMapping) {
					sourceMappedField = ((SeparateFieldMapping)fieldMapping).getInputField();
					sourceField = sourceMappedField.getField();
					sourceValue = sourceField.getValue();
					List<Object> sourceValues = separateValue(sourceValue);
					
					for(MappedField tmpTargetMappedField : ((SeparateFieldMapping)fieldMapping).getOutputFields().getMappedField()) {
						targetField = tmpTargetMappedField.getField();
						if(targetField instanceof JavaField) {
							JavaField jtf = (JavaField)targetField;
							FieldActions fieldActions = tmpTargetMappedField.getFieldActions();
							
							for(FieldAction fieldAction : fieldActions.getFieldAction()) {
								if(fieldAction instanceof MapAction) {
									Method targetMethod = targetClazz.getDeclaredMethod(jtf.getSetMethod(), AtlasModelFactory.classFromFieldType(jtf.getType()));
									targetMethod.invoke(targetObject, sourceValues.get(((MapAction)fieldAction).getIndex()));
								}
							}
						}
					}				
				}
			}
			session.setOutput(targetObject);

		} catch (Exception e) {
			throw new AtlasException(e.getMessage(), e.getCause());
		}
	}
	
	protected Object getParentObject(JavaPath javaPath, Object sourceObject) throws Exception {
		Object tmpObject = sourceObject;
		
		if(javaPath.getSegments() == null || javaPath.getSegments().size() < 2) {
			return sourceObject;
		}
		
		Class<?> tmpClass = sourceObject.getClass();
		int limit = javaPath.getSegments().size()-1;
		for(int i=0; i < limit; i++) {
			Method tmpMethod = tmpClass.getDeclaredMethod("get"+javaPath.getSegments().get(i));
			tmpObject = tmpMethod.invoke(tmpObject);
		}
		
		return tmpObject;
	}
	
	protected List<Object> separateValue(Object value) {
		
		List<Object> values = new ArrayList<Object>();
		if(value == null || !(value instanceof String)) {
			return values;
		}
		
		values.addAll(Arrays.asList(((String)value).split("\\s+", 512)));
		return values;
	}

	@Override
	public AtlasModuleMode getMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMode(AtlasModuleMode atlasModuleMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AtlasModuleMode> listSupportedModes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isStatisticsSupported() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isStatisticsEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
