package com.xjy.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author Salieri
 * @Date 2020/7/14 10:27
 * @Version 1.0
 */

@Component
public class PaymentFallbackService implements  PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "ok";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "timeout";
    }
}
