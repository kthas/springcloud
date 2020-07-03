package com.xjy.springcloud.controller;



import com.xjy.springcloud.entities.CommonResult;
import com.xjy.springcloud.entities.Payment;
import com.xjy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result > 0 ){
            return new CommonResult(200,"成功",result);
        }else{
            return new CommonResult(500,"失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);

        if(result != null){
            return new CommonResult(200,"成功"+serverPort,result);
        }else{
            return new CommonResult(200,"没有记录"+id,null);
        }
    }

    @GetMapping(value="/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }
}
