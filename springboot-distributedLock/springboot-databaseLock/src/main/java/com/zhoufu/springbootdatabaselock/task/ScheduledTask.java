package com.zhoufu.springbootdatabaselock.task;

import com.zhoufu.springbootdatabaselock.respository.CommodityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zf
 * @DateTime 2020/3/3 15:09
 * @Description
 **/
@Component
public class ScheduledTask {

    @Autowired
    CommodityRepository commodityRepository;

    Logger logger= LoggerFactory.getLogger(this.getClass());

//    @Scheduled(fixedDelay = 3000)
//    public void task1( ) {
//        try {
//            logger.info("time:"+System.currentTimeMillis());
//            Thread.sleep(10000L);
//            Commodity commodity=new Commodity();
//            commodity.setNumber(1);
//            commodity.setCommodityName("任务1");
//            commodityRepository.save(commodity);
//            logger.info("任务结束.....");
//        }catch (Exception e){
//            logger.error("出现异常");
//        }
//    }

}
