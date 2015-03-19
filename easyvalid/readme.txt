//使用方式
@Valid(regular=Regular.ISNULL, desc="#{pro}xxx${value}", order=1)

目前只能用于属性验证（目前不支持bean内部的嵌套bean验证）

	@Valid(regular=Regular.ISBLANK, desc="#{pro}不能为空", order=10)
	private String name;
	
	@Valids({
		@Valid(regular=Regular.ISNULL, desc="#{pro}不能为空"),
		@Valid(regular=Regular.ISBLANK, desc="#{pro}不能为空串")
	})
	private String desc;
	@Valids({
		@Valid(regular=Regular.ISNULL, desc="年龄不能为空"),
		@Valid(regular=Regular.REG, value="^[0-4]{0,1}[0-9]{1}$", desc="年龄只能在1-50之间,当前年龄是#{value}", order=8),
		@Valid(regular=Regular.BMETHOD, value="springBeanValid.validAge", desc="年龄只能小于30", order=9)
	})
	private Integer age;
	
bean必须有getXX方法，取值通过getXX方式取，验证过一次后 这个类的验证信息会缓存


详细请看 com.easyvalid.test.TestBean 以及 com.easyvalid.test.TestValid 这个测试！！！