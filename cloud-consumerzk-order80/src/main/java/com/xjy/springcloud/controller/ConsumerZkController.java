package com.xjy.springcloud.controller;


import com.xjy.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerZkController {

    @Resource
    private RestTemplate restTemplate;

    private static final String INVOKE_URL = "http://cloud-provider-payment";

    @RequestMapping(value = "/consumer/payment/zk")
    public CommonResult orderZk(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/zk",CommonResult.class);
    }
}
