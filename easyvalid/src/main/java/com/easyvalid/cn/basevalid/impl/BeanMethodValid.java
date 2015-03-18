package com.easyvalid.cn.basevalid.impl;

import java.lang.reflect.Method;

import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;
import com.easyvalid.cn.util.SpringContextUtil;

public class BeanMethodValid extends BaseValid implements IValid {

	private Object bean;
	private Method validMethod;
	private String methodStr;

	@SuppressWarnings("unchecked")
	public BeanMethodValid(String proName, String desc, String bmethod) {
		super(proName, desc);
		String[] beanAndMethod = bmethod.split("\\.");
		if (beanAndMethod.length != 2) {
			throw new RuntimeException(
					"bmethod的格式必须为xx.yy  其中xx指bean名字，yy指方法名 bmethod : "
							+ bmethod);
		}
		bean = SpringContextUtil.getBean(beanAndMethod[0]);
		if (bean == null) {
			throw new RuntimeException("不存在指定的bean ：" + beanAndMethod[0]);
		}

		try {
			validMethod = bean.getClass().getMethod(beanAndMethod[1],
					new Class[] { Object.class, Object.class });
		} catch (Exception e) {
			throw new RuntimeException("指定bean不存在该方法 ：public boolean "
					+ beanAndMethod[1] + "(Object srcObj,Object value)", e);
		}
		Class clazz = validMethod.getReturnType();
		if (clazz == null || !clazz.toString().equals("boolean")) {
			throw new RuntimeException("指定bean不存在该方法 ：" + bean.getClass()
					+ " public boolean " + beanAndMethod[1]
					+ "(Object srcObj,Object value)");

		}
		methodStr = bean.getClass().getName() + ". public boolean "
				+ beanAndMethod[1] + "(Object srcObj,Object value)";
	}

	@Override
	public ValidErrorBean valid(Object srcObj, Object value) {
		try {
			Boolean bool = (Boolean) this.validMethod.invoke(bean, srcObj,
					value);
			if (bool.booleanValue() == false) {
				return super.getValidErrorBean(value);
			}
		} catch (Exception e) {
			throw new RuntimeException("验证方法失败，请检查代码！！！" + methodStr, e);
		}
		return null;
	}

}
