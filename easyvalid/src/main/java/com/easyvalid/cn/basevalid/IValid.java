package com.easyvalid.cn.basevalid;

import com.easyvalid.cn.bean.ValidErrorBean;

public interface IValid {
    /**
     * 
     * @param srcObj 原始对象
     * @param proValue
     * @return
     */
    public ValidErrorBean valid(Object srcObj, Object proValue);
}
