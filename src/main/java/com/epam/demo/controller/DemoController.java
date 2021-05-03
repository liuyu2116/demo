package com.epam.demo.controller;

import com.epam.demo.enmu.SendResult;
import com.epam.demo.service.SendService;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
/**
 * @description（类描述）: 该类为调用短信接口的controller接口，实现了流量控制
 * @author（创建人）: yliu
 * @createDate（创建时间）: 2021/5/3
 * @version（版本）: v1.0
 */
@RestController
public class DemoController {
    @Autowired
    SendService sendService;
    /**
     * 创建一个限速器，每一秒钟，产生100个令牌
     */
    private RateLimiter rateLimiter = RateLimiter.create(100,1, TimeUnit.SECONDS);


    /**
     * @description: 接收发送请求并实现了流量控制，调用service类发送短信
     * @return : 返回发送是否成功的信息
     */
    @PostMapping(value = "/send")
    public String messageSend(){
        if (this.rateLimiter.tryAcquire()){
            //调取发送短信的服务类
            return sendService.send();
        }
        return SendResult.BLOCK.getMessage();
    }


}
