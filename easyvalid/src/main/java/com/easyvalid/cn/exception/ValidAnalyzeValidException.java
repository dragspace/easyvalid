package com.easyvalid.cn.exception;

/**
 * 
 * <p>
 * Description:	用于解析@valid标签是抛出的异常
 * </p>
 * @author xiaoruihu
 * @date 2015-3-20 下午07:48:15
 */
public class ValidAnalyzeValidException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValidAnalyzeValidException(String msg, Exception e){
        super(msg, e);
    }
}
