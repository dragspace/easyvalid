package com.easyvalid.cn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个字段上可以有多个验证注解
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Valids {
	/**
	 * 多个注解验证
	 * @return
	 */
	Valid[] value();
}
