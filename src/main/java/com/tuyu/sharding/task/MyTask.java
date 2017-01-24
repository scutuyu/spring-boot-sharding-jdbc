package com.tuyu.sharding.task;

import com.tuyu.sharding.util.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by tuyu on 1/12/17.
 */
@Component
public class MyTask {
    private int count = 0 ;

    @Scheduled(fixedRate = 1000,initialDelay = 5000)
    public void sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread() + "任务开始:");
        System.out.println(new Date() + " hello,tuyu! count= " + count++);
        Thread.sleep(2000l);
        System.out.println("睡眠2秒,任务结束！");
    }
}
