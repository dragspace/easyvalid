package com.easyvalid.cn.basevalid.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;

public class RegexValid extends BaseValid implements IValid {
	private Pattern p;

	/**
	 * 
	 * @param proName
	 * @param desc
	 * @param regex
	 */
	public RegexValid(String proName, String desc, String regex) {
		super(proName, desc);
		this.p = Pattern.compile(regex);
	}

	@Override
	public ValidErrorBean valid(Object srcObj, Object value) {
		
		if(value == null){
			return super.getValidErrorBean(value);
		}
		
		Matcher m = p.matcher(value.toString());
		if(m.matches()){
			return null;
		}else{
			return super.getValidErrorBean(value);
		}
	}
	

}
