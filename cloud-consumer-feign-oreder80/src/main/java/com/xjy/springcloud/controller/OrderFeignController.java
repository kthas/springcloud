package com.xjy.springcloud.controller;

import com.xjy.springcloud.entities.CommonResult;
import com.xjy.springcloud.entities.Payment;
import com.xjy.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Salieri
 * @Date 2020/7/3 16:27
 * @Version 1.0
 */

@RestController
@Slf4j
public class OrderFeignController {


    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public void feignTimeout(){
        paymentFeignService.feignTimeout();
    }

}
