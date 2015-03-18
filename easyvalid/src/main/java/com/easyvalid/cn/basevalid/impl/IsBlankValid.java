package com.easyvalid.cn.basevalid.impl;

import org.apache.commons.lang.StringUtils;

import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;

public class IsBlankValid extends BaseValid implements IValid {

	/**
	 * @param proName
	 * @param desc
	 */
	public IsBlankValid(String proName, String desc) {
		super(proName, desc);
	}

	@Override
	public ValidErrorBean valid(Object srcObj, Object value) {
		if (value == null || StringUtils.isBlank(value.toString())) {
			return super.getValidErrorBean(value);
		}
		return null;
	}

}
