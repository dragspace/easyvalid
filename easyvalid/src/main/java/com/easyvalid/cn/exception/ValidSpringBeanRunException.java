package com.easyvalid.cn.exception;


/**
 * 
 * <p>
 * Description:	用于springbean 验证方法抛出的异常
 * </p>
 * @author xiaoruihu
 * @date 2015-3-20 下午07:48:46
 */
public class ValidSpringBeanRunException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public ValidSpringBeanRunException(String msg, Exception e){
        super(msg, e);
    }
}
