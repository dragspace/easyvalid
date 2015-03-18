package com.easyvalid.test.springvalidbean;


public class SpringBeanValid {
	
	
	
	public SpringBeanValid() {
		super();
		System.out.println("验证class初始化");
	}

	/**
	 * 验证年龄
	 * @param srcObj
	 * @param value
	 * @return
	 */
	public boolean validAge(Object srcObj,Object value){
		//TestBean tb = (TestBean)srcObj;
		if(value == null){
			return false;
		}
		int age = Integer.parseInt(value.toString());
		return age > 30;
	}
	
	/**
	 * 验证bean的工作
	 * @param srcObj
	 * @param value
	 * @return
	 */
	public boolean validSpring(Object srcObj,Object value){
		//TestBean tb = (TestBean)srcObj;
		return "Spring".equals(value);
	}
	
}
