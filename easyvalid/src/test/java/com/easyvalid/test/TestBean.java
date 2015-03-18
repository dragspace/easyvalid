package com.easyvalid.test;

import com.easyvalid.cn.annotation.Regular;
import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.annotation.Valids;

public class TestBean {

	@Valid(regular = Regular.ISBLANK, desc = "#{pro}不能为空", order = 10)
	private String name;

	@Valids( { @Valid(regular = Regular.ISNULL, desc = "#{pro}不能为空"),
			@Valid(regular = Regular.ISBLANK, desc = "#{pro}不能为空串") })
	private String desc;
	@Valids( {
			@Valid(regular = Regular.ISNULL, desc = "年龄不能为空"),
			@Valid(regular = Regular.REG, value = "^[0-4]{0,1}[0-9]{1}$", desc = "年龄只能在1-50之间,当前年龄是#{value}", order = 8),
			@Valid(regular = Regular.BMETHOD, value = "springBeanValid.validAge", desc = "年龄只能小于30", order = 9) })
	private Integer age;

	@Valid(regular = Regular.BMETHOD, value = "springBeanValid.validSpring", desc = "#{pro}的值只能为Spring")
	private String spring;

	public String getSpring() {
		return spring;
	}

	public void setSpring(String spring) {
		this.spring = spring;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
