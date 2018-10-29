package com.wzc.springwebsocketdemo.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wangzhicheng
 * Date: 2018-10-29
 * Time: 11:48
 */
public class HelloMessage {

    private String toUser;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
}
