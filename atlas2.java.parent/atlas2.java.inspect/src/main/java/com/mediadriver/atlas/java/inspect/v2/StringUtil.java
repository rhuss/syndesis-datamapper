package com.mediadriver.atlas.java.inspect.v2;

public class StringUtil {

	public static boolean isEmpty(String s) {
		return s == null || "".equals(s.trim());
	}
	
	public static String capitalizeFirstLetter(String sentence) {
		if (StringUtil.isEmpty(sentence)) {
			return sentence;
		}
		if (sentence.length() == 1) {
			return String.valueOf(sentence.charAt(0)).toUpperCase();
		}
		return String.valueOf(sentence.charAt(0)).toUpperCase() + sentence.substring(1);
	}
	
	public static String removeGetterAndLowercaseFirstLetter(String getter) {
		if(StringUtil.isEmpty(getter)) {
			return getter;
		}
		
		String subGetter = new String();
		if(getter.startsWith("get")) {
			if (getter.length() <= "get".length()) {
				return getter;
			}
			subGetter = getter.substring("get".length());
		} else if(getter.startsWith("is")) {
			if (getter.length() <= "is".length()) {
				return getter;
			}
			subGetter = getter.substring("is".length());
		} else {
			return getter;
		}
		
		return String.valueOf(subGetter.charAt(0)).toLowerCase() + subGetter.substring(1);
	}
	
	public static String removeSetterAndLowercaseFirstLetter(String setter) {
		if(StringUtil.isEmpty(setter)) {
			return setter;
		}
		
		String subSetter = new String();
		if(setter.startsWith("set")) {
			if (setter.length() <= "set".length()) {
				return setter;
			}
			subSetter = setter.substring("set".length());
		} else {
			return setter;
		}
		
		return String.valueOf(subSetter.charAt(0)).toLowerCase() + subSetter.substring(1);
	}
}
