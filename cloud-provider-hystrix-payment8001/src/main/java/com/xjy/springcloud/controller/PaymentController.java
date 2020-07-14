package com.xjy.springcloud.controller;

import com.xjy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Salieri
 * @Date 2020/7/6 15:19
 * @Version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        log.info(paymentService.paymentInfo_ok(id));
        return paymentService.paymentInfo_ok(id);
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        log.info(paymentService.paymentInfo_Timeout(id));
        return paymentService.paymentInfo_Timeout(id);
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuit(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("服务熔断结果"+result);
        return result;
    }
}
