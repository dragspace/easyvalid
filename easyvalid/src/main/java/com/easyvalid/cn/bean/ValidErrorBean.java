package com.easyvalid.cn.bean;

public class ValidErrorBean {
	/**
	 * 验证失败的属性名称
	 */
	private String proName;
	/**
	 * 验证失败描述
	 */
	private String description;

	public ValidErrorBean(String proName, String description) {
		this.proName = proName;
		this.description = description;
	}

	public String getProName() {
		return proName;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "ValidErrorBean [description=" + description + ", proName="
				+ proName + "]";
	}

}
