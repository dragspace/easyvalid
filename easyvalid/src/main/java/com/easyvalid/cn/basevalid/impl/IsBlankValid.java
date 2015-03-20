package com.easyvalid.cn.basevalid.impl;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;

public class IsBlankValid extends BaseValid implements IValid {

    public IsBlankValid(Class<?> clazz, Field field, Valid valid) {
        super(clazz, field, valid);
    }

    @Override
    public ValidErrorBean valid(Object srcObj, Object value) {
        if (value == null || StringUtils.isBlank(value.toString())) {
            return super.getValidErrorBean(value);
        }
        return null;
    }

}
