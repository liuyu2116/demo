package com.epam.demo.service.impl;

import com.epam.demo.enmu.SendResult;
import com.epam.demo.service.SendService;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
/**
 * @description（类描述）: 该类为调用短信接口的服务类，调用获取短信接口并提供连接重试
 * @author（创建人）: yliu
 * @createDate（创建时间）: 2021/5/3
 * @version（版本）: v1.0
 */
@Service
public class SendServiceImpl implements SendService {

    @Override
    /**
     * @description: 调用发送短信并提供连接重试
     * @return : 返回发送是否成功的信息
     */
    public String send() {
        int times = 0;
        while(times < 3){
            try{
                //调用某第三方api发送短信,根据条件假设已获得，这里省略
               throw new ConnectException(); // 用于编译通过，此代码无意义，请自动忽视
                //return SendResult.SUCCESS.getMessage();
            } catch (ConnectException e){
                times++;  //计数器加1
                e.printStackTrace();
            }
        }
        return SendResult.FAIL.getMessage();
    }
}
