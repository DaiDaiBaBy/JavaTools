package com.zhoufu.springbootmockito;

import com.zhoufu.springbootmockito.biz.store.dao.IStoreDao;
import com.zhoufu.springbootmockito.biz.store.service.impl.StoreServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 以mockito环境启动
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StoreServiceImplMockitoTest {
    /**
     * InjectMocks
     * 注入storeService对象到bean容器中
     *
     * 注意，与Autowire不同的是，InjectMocks可以跟Mock注解搭配使用
     */
    @InjectMocks
    StoreServiceImpl storeService;
    /**
     * 创建mock对象，自动被注入到被@InjectMocks注解所修饰的对象里
     */
    @Mock
    IStoreDao storeDao;

    @Test
    public void testCountLeftGoods(){
        String goodsId = "1";
        // 方法打桩
        // 若调用了storeDao.countLeftGoods方法，则不管传入任意String值，都会返回整形1
        Mockito.when(storeDao.countLeftGoods(Mockito.any())).thenReturn(1);
        Assert.assertEquals(Integer.valueOf(1), storeService.countLeftGoods(goodsId));
    }
}