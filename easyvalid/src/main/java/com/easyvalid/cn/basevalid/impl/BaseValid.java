package com.easyvalid.cn.basevalid.impl;

import java.lang.reflect.Field;

import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.bean.ValidErrorBean;

public abstract class BaseValid {
    
    /**
     * 验证类的class
     */
    protected Class<?> clazz;
    /**
     * 验证字段
     */
    protected Field field;
    /**
     * 验证的注解
     */
    protected Valid valid;

    protected String desc;
    protected String proName;

    public BaseValid(Class<?> clazz, Field field, Valid valid) {
        this.clazz = clazz;
        this.field = field;
        this.valid = valid;
        
        this.proName = field.getName();
        this.desc = valid.desc().replace("#{pro}", proName);
       
    }


    /**
     * <p>
     *  获取一个验证失败的信息
     * </p>
     * @author xiaoruihu
     * @date 2015-3-20 下午06:57:45
     * @param value  验证的值
     * @return
     */
    public ValidErrorBean getValidErrorBean(Object value) {
        String tempValue = null;
        if (value == null) {
            tempValue = "null";
        } else {
            tempValue = value.toString();
        }
        String description = this.desc.replace("#{value}", tempValue);
        return new ValidErrorBean(proName, description);
    }
}
