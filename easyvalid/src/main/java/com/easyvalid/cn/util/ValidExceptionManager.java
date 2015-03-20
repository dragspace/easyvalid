package com.easyvalid.cn.util;

import com.easyvalid.cn.exception.ValidAnalyzeValidException;
import com.easyvalid.cn.exception.ValidSpringBeanRunException;

/**
 * 
 * <p>
 * Description: 抛出各种解析及验证异常
 * </p>
 * 
 * @author xiaoruihu
 * @date 2015-3-20 下午07:37:50
 */
public class ValidExceptionManager {

    public static void ValidAnalyzeValidException(String msg, Exception e) {
        throw new ValidAnalyzeValidException(msg, e);
    }

    public static void ValidSpringBeanRunException(String msg, Exception e) {
        throw new ValidSpringBeanRunException(msg, e);
    }
}
