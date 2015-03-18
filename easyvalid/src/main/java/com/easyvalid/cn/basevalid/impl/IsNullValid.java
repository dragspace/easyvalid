package com.easyvalid.cn.basevalid.impl;

import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;

public class IsNullValid extends BaseValid implements IValid {

	/**
	 * 
	 * @param proName
	 * @param desc
	 */
	public IsNullValid(String proName, String desc) {
		super(proName, desc);
	}

	@Override
	public ValidErrorBean valid(Object srcObj, Object value) {
		if (value == null) {
			return super.getValidErrorBean(value);
		}
		return null;
	}

}
