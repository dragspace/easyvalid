package com.easyvalid.cn.basevalid.impl;

import java.lang.reflect.Field;

import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;

public class IsNullValid extends BaseValid implements IValid {

	public IsNullValid(Class<?> clazz, Field field, Valid valid) {
	    super(clazz, field, valid);
	}

	@Override
	public ValidErrorBean valid(Object srcObj, Object value) {
		if (value == null) {
			return super.getValidErrorBean(value);
		}
		return null;
	}

}
