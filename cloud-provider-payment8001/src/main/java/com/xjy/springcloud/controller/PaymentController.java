package com.xjy.springcloud.controller;



import com.xjy.springcloud.entities.CommonResult;
import com.xjy.springcloud.entities.Payment;
import com.xjy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Slf4j
@ResponseBody
public class PaymentController {

    @Resource
    private PaymentService paymentService;


    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果"+result);
        if (result > 0 ){
            return new CommonResult(200,"成功",result);
        }else{
            return new CommonResult(500,"失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果"+result);
        if(result != null){
            return new CommonResult(200,"成功",result);
        }else{
            return new CommonResult(200,"没有记录"+id,null);
        }
    }
}
