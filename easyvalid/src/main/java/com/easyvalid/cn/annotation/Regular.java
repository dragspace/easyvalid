package com.easyvalid.cn.annotation;

public enum Regular {
	
	/**
	 * 空串或者null
	 */
	ISBLANK(),
	/**
	 * null
	 */
	ISNULL(),
	/**
	 * 正则表达式(regex)
	 */
	REG(),
	/**
	 * springbean的验证
	 */
	BMETHOD();
}
