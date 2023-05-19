package com.zhoufu.springbootdynamictask.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;
/**
 * @ClassName : SpringJobFactory
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:35
 * @Description : 解决spring bean注入Job的问题
 */
@Component
public class SpringJobFactory extends AdaptableJobFactory {
    private final AutowireCapableBeanFactory capableBeanFactory;
    @Autowired
    public SpringJobFactory(AutowireCapableBeanFactory capableBeanFactory) {
        this.capableBeanFactory = capableBeanFactory;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}