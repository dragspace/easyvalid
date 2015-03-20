package com.easyvalid.cn.basevalid.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;
import com.easyvalid.cn.util.SpringContextUtil;
import com.easyvalid.cn.util.ValidExceptionManager;

public class BeanMethodValid extends BaseValid implements IValid {

    private Object bean;
    private Method validMethod;
    private String methodStr;
    
    private static final String PARAM = "(Object srcObj,Object value)";

    public BeanMethodValid(Class<?> clazz, Field field, Valid valid) {
        super(clazz, field, valid);
        String[] beanAndMethod = valid.value().split("\\.");
        if (beanAndMethod.length != 2) {
            ValidExceptionManager.ValidAnalyzeValidException(
                    "bmethod的格式必须为xx.yy  其中xx指bean名字，yy指方法名 bmethod : " + valid.value(), null);
        }
        bean = SpringContextUtil.getBean(beanAndMethod[0]);
        if (bean == null) {
            ValidExceptionManager.ValidAnalyzeValidException("不存在指定的bean ：" + beanAndMethod[0],
                    null);
        }

        try {
            validMethod =
                    bean.getClass().getMethod(beanAndMethod[1],
                            new Class[] {Object.class, Object.class});
        } catch (Exception e) {
            ValidExceptionManager.ValidAnalyzeValidException("指定bean不存在该方法 ：public boolean "
                    + beanAndMethod[1] + PARAM, e);
        }
        Class<?> methodResultClass = validMethod.getReturnType();
        if (methodResultClass == null || !"boolean".equals(methodResultClass.toString())) {
            ValidExceptionManager.ValidAnalyzeValidException("指定bean不存在该方法 ：" + bean.getClass()
                    + " public boolean " + beanAndMethod[1] + PARAM, null);

        }
        methodStr =
                bean.getClass().getName() + ". public boolean " + beanAndMethod[1]
                        + "(Object srcObj,Object value)";
    }

    @Override
    public ValidErrorBean valid(Object srcObj, Object value) {
        try {
            Boolean bool = (Boolean) this.validMethod.invoke(bean, srcObj, value);
            if (!bool.booleanValue()) {
                return super.getValidErrorBean(value);
            }
        } catch (Exception e) {
            ValidExceptionManager.ValidSpringBeanRunException("验证方法失败，请检查代码！！！" + methodStr, e);
        }
        return null;
    }

}
