package com.easyvalid.test.springvalidbean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 测试验证类
 * @author Administrator
 *
 */
public class SpringBeanValid {
	private static final Log LOG = LogFactory.getLog(SpringBeanValid.class);
	public SpringBeanValid() {
		super();
		LOG.info("--------------------验证class SpringBeanValid 初始化-------------------------");
	}

	/**
	 * 验证年龄
	 * 
	 * @param srcObj
	 * @param value
	 * @return
	 */
	public boolean validAge(Object srcObj, Object value) {
	 // srcObj is TestBean;
		if (value == null) {
			return false;
		}
		int age = Integer.parseInt(value.toString());
		return age > 30;
	}

	/**
	 * 验证bean的工作
	 * 
	 * @param srcObj
	 * @param value
	 * @return
	 */
	public boolean validSpring(Object srcObj, Object value) {
		// srcObj is TestBean;
		return "Spring".equals(value);
	}

}
