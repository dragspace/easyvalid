package com.easyvalid.cn.annotation;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.basevalid.impl.BeanMethodValid;
import com.easyvalid.cn.basevalid.impl.IsBlankValid;
import com.easyvalid.cn.basevalid.impl.IsNullValid;
import com.easyvalid.cn.basevalid.impl.RegexValid;

public enum Regular {

    /**
     * 空串或者null
     */
    ISBLANK() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            return new IsBlankValid(field.getName(), valid.desc());
        }
    },
    /**
     * null
     */
    ISNULL() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            return new IsNullValid(field.getName(), valid.desc());
        }
    },
    /**
     * 正则表达式(regex)
     */
    REG() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            if (StringUtils.isBlank(valid.value())) {
                throw new RuntimeException("@valid 的regular属性为REG， 但是他的value 为空, class : "
                        + clazz.getName() + " 属性：" + field.getName());
            }
            return new RegexValid(field.getName(), valid.desc(), valid.value());
        }
    },
    /**
     * springbean的验证
     */
    BMETHOD() {
        @Override
        public IValid getValid(Class<?> clazz, Field field, Valid valid) {
            return new BeanMethodValid(field.getName(), valid.desc(), valid.value());
        }
    };

    public abstract IValid getValid(Class<?> clazz, Field field, Valid valid);
}
