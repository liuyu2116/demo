package com.epam.demo.controller;

import com.epam.demo.enmu.SendResult;
import com.epam.demo.service.SendService;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @description: 该类为调用短信接口的controller接口，实现了流量控制
 * @author: yliu
 * @createDate: 2021/5/3
 * @version: v1.0
 */
@RestController
public class DemoController {
    @Autowired
    SendService sendService;
    /**
     * 创建一个限速器，每一秒钟，产生100个令牌
     */
    private RateLimiter rateLimiter = RateLimiter.create(100);


    /**
     * @description: 接收发送请求并实现了流量控制，调用service类发送短信
     * @return : 返回发送是否成功的信息
     */
    @PostMapping(value = "/send")
    public String messageSend(){
        //尝试获取令牌，根据获取结果返回true或false
        if (this.rateLimiter.tryAcquire()){
            //调取发送短信的服务类
            return sendService.send()==true ? SendResult.SUCCESS.getMessage() :  SendResult.FAIL.getMessage();
        }
        return SendResult.BLOCK.getMessage();
    }
}
