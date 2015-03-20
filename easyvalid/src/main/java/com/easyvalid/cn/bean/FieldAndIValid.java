package com.easyvalid.cn.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.util.ValidExceptionManager;

/**
 * 
 * <p>
 * Description: 待验证属性的get方法和验证信息的封装
 * </p>
 * 
 * @author xiaoruihu
 * @date 2015-3-20 下午07:02:35
 */
public class FieldAndIValid {
    /**
     * 待验证属性的get方法
     */
    private Method getMethod;
    /**
     * 属性的验证接口
     */
    private IValid ivalid;
    /**
     * 属性的验证顺序
     */
    private int order;

    @SuppressWarnings("unchecked")
    public FieldAndIValid(Class clazz, Field field, IValid ivalid, Valid valid) {
        this.ivalid = ivalid;

        String fieldName = field.getName();
        // 获得和属性对应的getXXX()方法的名字
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        // 获得和属性对应的setXXX()方法的名字
        String getMethodName = "get" + firstLetter + fieldName.substring(1);
        // 获得和属性对应的setXXX()方法
        try {
            this.getMethod = clazz.getMethod(getMethodName, new Class[] {});
        } catch (Exception e) {
            ValidExceptionManager.ValidAnalyzeValidException("获取get方法失败，class " + clazz.getName()
                    + " 属性 ： " + field.getName(), e);
        }
        this.order = valid.order();
    }

    public IValid getIvalid() {
        return ivalid;
    }

    public Method getGetMethod() {
        return getMethod;
    }

    public int getOrder() {
        return order;
    }

}
