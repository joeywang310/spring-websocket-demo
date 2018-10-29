package com.wzc.springwebsocketdemo.controller;

import com.wzc.springwebsocketdemo.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wangzhicheng
 * Date: 2018-10-26
 * Time: 16:54
 */
@Controller
public class IndexController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/queue/hello")
    @SendTo("/topic/first")
    public String hello (String message) {
        System.out.println(message);
        return message;
    }

    @MessageMapping("/user/queue/messaging")
    public void single (Principal principal, HelloMessage message) {
        System.out.println(message);
        simpMessagingTemplate.convertAndSendToUser(message.getToUser(),"/queue/messaging",message);
    }


    @MessageMapping("/greeting")
    public void greeting(Principal principal){
        String reply = "hello " + principal.getName();
        System.out.println("sending " + reply);
        simpMessagingTemplate.convertAndSend("/user/{username}/reply", reply);
//        simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue/queue1",message);
    }
}