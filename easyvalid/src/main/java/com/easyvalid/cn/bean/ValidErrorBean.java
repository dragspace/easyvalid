package com.easyvalid.cn.bean;

/**
 * 
 * <p>
 * Description:验证失败后 返回该实体	
 * </p>
 * @author xiaoruihu
 * @date 2015-3-20 下午07:04:23
 */
public class ValidErrorBean {
    /**
     * 验证失败的属性名称
     */
    private String proName;
    /**
     * 验证失败描述
     */
    private String description;

    public ValidErrorBean(String proName, String description) {
        this.proName = proName;
        this.description = description;
    }

    public String getProName() {
        return proName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ValidErrorBean [description=" + description + ", proName=" + proName + "]";
    }

}
