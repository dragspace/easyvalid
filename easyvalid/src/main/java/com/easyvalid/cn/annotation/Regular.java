package com.easyvalid.cn.annotation;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.basevalid.impl.BeanMethodValid;
import com.easyvalid.cn.basevalid.impl.IsBlankValid;
import com.easyvalid.cn.basevalid.impl.IsNullValid;
import com.easyvalid.cn.basevalid.impl.RegexValid;
import com.easyvalid.cn.util.ValidExceptionManager;

public enum Regular {

    /**
     * 空串或者null
     */
    ISBLANK() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            return new IsBlankValid(clazz, field, valid);
        }
    },
    /**
     * null
     */
    ISNULL() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            return new IsNullValid(clazz, field, valid);
        }
    },
    /**
     * 正则表达式(regex)
     */
    REG() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            if (StringUtils.isBlank(valid.value())) {
                ValidExceptionManager.ValidAnalyzeValidException(
                        "@valid 的regular属性为REG， 但是他的value 为空, class : " + clazz.getName() + " 属性："
                                + field.getName(), null);
            }
            return new RegexValid(clazz, field, valid);
        }
    },
    /**
     * springbean的验证
     */
    BMETHOD() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            return new BeanMethodValid(clazz, field, valid);
        }
    };

    public abstract IValid getValid(Class<?> clazz, Field field, Valid valid);
}
