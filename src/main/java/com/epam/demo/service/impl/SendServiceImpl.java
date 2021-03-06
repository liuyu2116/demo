package com.epam.demo.service.impl;

import com.epam.demo.enmu.SendResult;
import com.epam.demo.help.Message;
import com.epam.demo.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
/**
 * @description: 该类为调用短信接口的服务类，调用获取短信接口并提供连接重试
 * @author: yliu
 * @createDate: 2021/5/3
 * @version: v1.0
 */
@Service
public class SendServiceImpl implements SendService {
    @Autowired
    Message message;

    private final int RETRY_TIMES = 3;


    /**
     * @description: 调用发送短信并提供连接重试
     * @return : 返回发送是否成功
     */
    @Override
    public Boolean send() {
        int times = 0;
        while (times < RETRY_TIMES) {
            try{
                //调用某第三方api发送短信,根据条件假设已获得，这里省略
                message.send();
                return true;
            } catch (ConnectException e){
                times++;  //计数器加1
                e.printStackTrace();
            }
        }
        return false;
    }
}
