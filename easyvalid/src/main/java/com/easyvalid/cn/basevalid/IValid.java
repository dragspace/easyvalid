package com.easyvalid.cn.basevalid;

import com.easyvalid.cn.bean.ValidErrorBean;

/**
 * 
 * <p>
 * Description:	验证属性接口
 * </p>
 * @author xiaoruihu
 * @date 2015-3-20 下午07:01:09
 */
public interface IValid {
    /**
     * 
     * @param srcObj 原始对象
     * @param proValue
     * @return
     */
    public ValidErrorBean valid(Object srcObj, Object proValue);
}
