package com.easyvalid.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easyvalid.cn.bean.ValidErrorBean;
import com.easyvalid.cn.util.ValidManager;

public class TestValid {

	@BeforeClass
	public static void beforeTest(){
		new ClassPathXmlApplicationContext("application.xml");
	}
	
	@Test
	public void testValidOne() {
		TestBean a = new TestBean();
		a.setName("  ");
		a.setDesc("testOne");
		a.setAge(70);
		ValidErrorBean veb = ValidManager.validOne(a);
		System.out.println(veb);
	}
	
	@Test
	public void testValidAll(){
		TestBean a = new TestBean();
		a.setName("  ");
		a.setDesc("testAll");
		a.setAge(70);
		List<ValidErrorBean> errorList = ValidManager.validAll(a);
		System.out.println(errorList);
	}
	
	@Test
	public void testOrder(){
		TestBean a = new TestBean();
		List<ValidErrorBean> errorList = ValidManager.validAll(a);
		System.out.println(errorList);
	}
	
}
