package com.easyvalid.cn.basevalid.impl;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.bean.ValidErrorBean;

public class RegexValid extends BaseValid implements IValid {
    private Pattern p;

    public RegexValid(Class<?> clazz, Field field, Valid valid) {
        super(clazz, field, valid);
        this.p = Pattern.compile(valid.value());
    }

    @Override
    public ValidErrorBean valid(Object srcObj, Object value) {

        if (value == null) {
            return super.getValidErrorBean(value);
        }

        Matcher m = p.matcher(value.toString());
        if (m.matches()) {
            return null;
        } else {
            return super.getValidErrorBean(value);
        }
    }


}
