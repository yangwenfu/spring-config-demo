package com.example.configclient.poi;


import com.google.common.collect.Maps;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

public final class ReflectionUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

	public ReflectionUtil() {
	}

	public static List<Field> getFieldsIncludingSuperClasses(Class<?> clazz) {
		return getAllFieldsRec(clazz, new ArrayList());
	}

	public static Method getGetter(Class<?> clazz, Field field) {
		String filedName = field.getName();
		String firstLetter = filedName.substring(0, 1).toUpperCase();
		String getMethodName = "get" + firstLetter + filedName.substring(1);
		Method getMethod = null;

		try {
			getMethod = clazz.getDeclaredMethod(getMethodName);
		} catch (Exception var7) {
			LOGGER.error("error while get getter", var7);
		}

		return getMethod;
	}

	public static Method getSetter(Class<?> clazz, Field field) {
		Class<?> fieldType = field.getType();
		String filedName = field.getName();
		String firstLetter = filedName.substring(0, 1).toUpperCase();
		String setMethodName = "set" + firstLetter + filedName.substring(1);
		Method setMethod = null;

		try {
			setMethod = clazz.getDeclaredMethod(setMethodName, fieldType);
		} catch (Exception var8) {
			LOGGER.error("error while get setter", var8);
		}

		return setMethod;
	}

	public static Map<String, String> getFieldMap(Object o) {
		Class<?> clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Map<String, String> fieldMap = Maps.newHashMap();
		Field[] var4 = fields;
		int var5 = fields.length;

		for(int var6 = 0; var6 < var5; ++var6) {
			Field field = var4[var6];
			String fieldName = field.getName().toUpperCase();
			Method getMethod = getGetter(clazz, field);
			String fieldValue = null;

			try {
				fieldValue = (String)getMethod.invoke(o);
			} catch (Exception var12) {
				LOGGER.error("error while get: {}", var12, fieldName);
			}

			if (null != fieldValue) {
				fieldMap.put(fieldName, fieldValue);
			}
		}

		return fieldMap;
	}

	private static List<Field> getAllFieldsRec(Class<?> clazz, List<Field> fields) {
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null) {
			getAllFieldsRec(superClazz, fields);
		}

		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		return fields;
	}

	public static Class<?> getGenericParamClass(Class<?> clazz) {
		Type type = ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments()[0];
		return (Class)type;
	}

	public static void invokeSetter(Object obj, String propertyName, Object value) {
		Object object = obj;
		String[] names = StringUtils.split(propertyName, ".");

		for(int i = 0; i < names.length; ++i) {
			String getterMethodName;
			if (i < names.length - 1) {
				getterMethodName = "get" + StringUtils.capitalize(names[i]);
				object = invokeMethod(object, getterMethodName, new Class[0], new Object[0]);
			} else {
				getterMethodName = "set" + StringUtils.capitalize(names[i]);
				invokeMethodByName(object, getterMethodName, new Object[]{value});
			}
		}

	}

	public static Object invokeMethodByName(Object obj, String methodName, Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		} else {
			try {
				return method.invoke(obj, args);
			} catch (Exception var5) {
				LOGGER.error("error while method invoke: {}", var5);
				return null;
			}
		}
	}

	public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		} else {
			try {
				return method.invoke(obj, args);
			} catch (Exception var6) {
				LOGGER.error("error while method invoke: {}", var6);
				return null;
			}
		}
	}

	public static Method getAccessibleMethod(Object obj, String methodName, Class... parameterTypes) {
		Validate.notNull(obj, "object can't be null");
		Validate.notEmpty(methodName, "methodName can't be blank");
		Class searchType = obj.getClass();

		while(searchType != Object.class) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				ReflectionUtils.makeAccessible(method);
				return method;
			} catch (NoSuchMethodException var5) {
				searchType = searchType.getSuperclass();
			}
		}

		return null;
	}

	public static Method getAccessibleMethodByName(Object obj, String methodName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notEmpty(methodName, "methodName can't be blank");

		for(Class searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			Method[] var4 = methods;
			int var5 = methods.length;

			for(int var6 = 0; var6 < var5; ++var6) {
				Method method = var4[var6];
				if (method.getName().equals(methodName)) {
					ReflectionUtils.makeAccessible(method);
					return method;
				}
			}
		}

		return null;
	}
}
