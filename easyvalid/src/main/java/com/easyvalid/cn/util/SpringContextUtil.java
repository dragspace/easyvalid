package com.easyvalid.cn.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	 
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }
 
    public static <T> T getBean(String beanName, Class<T> clazs) {
        return clazs.cast(getBean(beanName));
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

}
