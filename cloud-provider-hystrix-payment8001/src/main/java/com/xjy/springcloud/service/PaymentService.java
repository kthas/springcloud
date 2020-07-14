package com.xjy.springcloud.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author Salieri
 * @Date 2020/7/6 15:14
 * @Version 1.0
 */
@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_ok(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_ok"+id;
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
           @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_Timeout"+id;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeoutHandler"+"响应超时";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name= "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name= "circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
            @HystrixProperty(name= "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id ){
        if(id<0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber  = IdUtil.simpleUUID();
        return "线程数"+Thread.currentThread().getName()+"\t"+"流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id ){
        return "id不能为负数，当前id:"+id;
    }

}
