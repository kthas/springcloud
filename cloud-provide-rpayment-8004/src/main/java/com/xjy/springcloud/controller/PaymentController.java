package com.xjy.springcloud.controller;


import com.xjy.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;


    @RequestMapping(value = "/payment/zk")
    public CommonResult paymentZk(){
        CommonResult result = new CommonResult();
        result.setMessage(port+":"+ UUID.randomUUID());
        return result;
    }
}
