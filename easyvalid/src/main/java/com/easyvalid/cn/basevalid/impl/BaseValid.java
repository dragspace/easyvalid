package com.easyvalid.cn.basevalid.impl;

import com.easyvalid.cn.bean.ValidErrorBean;
import com.easyvalid.cn.util.ValidConstant;

public abstract class BaseValid {

	protected String desc;
	protected String proName;

	public BaseValid(String proName, String desc) {
		this.desc = desc;
		this.proName = proName;

		this.proName = proName;
		if (desc == null) {
			this.desc = ValidConstant.DEFAULTVALIDDESC;
		}
		this.desc = this.desc.replace("#{pro}", proName);
	}

	public ValidErrorBean getValidErrorBean(Object value) {
		String tempValue = null;
		if (value == null) {
			tempValue = "null";
		} else {
			tempValue = value.toString();
		}
		String description = this.desc.replace("#{value}", tempValue);
		return new ValidErrorBean(proName, description);
	}
}
