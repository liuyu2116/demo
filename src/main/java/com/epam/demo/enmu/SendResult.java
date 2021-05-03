package com.epam.demo.enmu;
/**
 * @description（类描述）: 该类为接口调用状态的枚举类，提供了可能会出现的各种调用状态的枚举
 * @author（创建人）: yliu
 * @createDate（创建时间）: 2021/5/3
 * @version（版本）: v1.0
 */
public enum SendResult {
    SUCCESS(200, "短信发送成功，请接收"),
    FAIL(500, "服务器异常，请等待"),
    BLOCK(404,"服务器繁忙，请等待后重新发送");

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    SendResult(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
