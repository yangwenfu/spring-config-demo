package com.example.configclient.mybatisPlus.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/**
 *  注入公共字段自动填充,任选注入方式即可
 */

public class MyMetaObjectHandler extends MetaObjectHandler {

	protected final static Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);
	private final static String TIME="time";
	private final static String CREATETIME="createTime";
	private final static String LASTUPDATETime = "lastUpdateTime";
	@Override
	public void insertFill(MetaObject metaObject) {
//		Object originalObject = metaObject.getOriginalObject();
//		Class<?> aClass = originalObject.getClass();
//		Method[] methods = aClass.getMethods();
//		for (Method method : methods) {
//			if (method.getName().equals("setCreateTime")){
//				try {
//					method.invoke(originalObject,new Date());
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//				}
//			}
//		}

		if (metaObject.hasSetter(CREATETIME)) {
			setFieldValByName(CREATETIME, new Date(), metaObject);  //更新时间
//			setFieldValByName(CREATETIME, SecurityUtils.getLocalUserId(), metaObject); //更新人
		}
		if (metaObject.hasSetter(TIME)) {
			setFieldValByName(TIME, new Date(), metaObject);  //更新时间
//			setFieldValByName(CREATETIME, SecurityUtils.getLocalUserId(), metaObject); //更新人
		}
		if (metaObject.hasSetter("address")) {
			setFieldValByName("address", "浙江", metaObject);  //更新时间
//			setFieldValByName(CREATETIME, SecurityUtils.getLocalUserId(), metaObject); //更新人
		}

		metaObject.setValue("address", "浙江");
		logger.info("新增的时候干点不可描述的事情");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		logger.info("更新的时候干点不可描述的事情");
	}
}