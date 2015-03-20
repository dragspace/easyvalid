package com.easyvalid.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easyvalid.cn.bean.ValidErrorBean;
import com.easyvalid.cn.util.ValidManager;

/**
 * 测试用例
 * 
 * @author Administrator
 * 
 */
public class TestValid {
    private static final Log LOG = LogFactory.getLog(TestValid.class);

    @BeforeClass
    public static void beforeTest() {
        new ClassPathXmlApplicationContext("application.xml");
    }

    @Test
    public void testValidOne() {
        LOG.info("开始测试 testValidOne");
        TestBean a = new TestBean();
        a.setName("  ");
        a.setDesc("testOne");
        a.setAge(70);
        ValidErrorBean veb = ValidManager.validOne(a);
        LOG.info(veb);
        Assert.assertNotNull(veb);
        LOG.info("测试 testValidOne结束");
    }

    @Test
    public void testValidAll() {
        LOG.info("开始测试 testValidAll");
        TestBean a = new TestBean();
        a.setName("  ");
        a.setDesc("testAll");
        a.setAge(70);
        List<ValidErrorBean> errorList = ValidManager.validAll(a);
        LOG.info(errorList);
        Assert.assertEquals(3, errorList.size());
        LOG.info("测试 testValidAll结束");
    }

    @Test
    public void testOrder() {
        LOG.info("开始测试 testOrder");
        TestBean a = new TestBean();
        List<ValidErrorBean> errorList = ValidManager.validAll(a);
        LOG.info(errorList);
        Assert.assertEquals(4, errorList.size());
        LOG.info("测试 testOrder结束");
    }

}
