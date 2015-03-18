package com.easyvalid.cn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.easyvalid.cn.util.ValidConstant;


/**
 * 验证注解
 * 
 * @author Administrator
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.ANNOTATION_TYPE, ElementType.FIELD })
public @interface Valid {

	/**
	 * 验证器
	 */
	Regular regular();

	/**
	 * 验证器的值 （目前只有当 regular= BMETHOD 和 REG 时才有效）
	 */
	String value() default "";

	/**
	 * 验证失败后返回的消息
	 */
	String desc() default ValidConstant.DEFAULTVALIDDESC;
	
	/**
	 * 校验顺序
	 */
	int order() default 0;
}
