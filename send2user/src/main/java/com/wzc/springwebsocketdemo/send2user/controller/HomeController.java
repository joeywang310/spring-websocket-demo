package com.wzc.springwebsocketdemo.send2user.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wangzhicheng
 * Date: 2018-10-29
 * Time: 16:39
 */
@Controller
public class HomeController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 模拟登录     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, String name, String pwd){
        HttpSession httpSession = request.getSession();
        // 如果登录成功，则保存到会话中
        httpSession.setAttribute("loginName", name);
        return "success";
    }

    /**
     * 向执行用户发送请求
     */
    @MessageMapping(value = "/topic/chat")
    public int sendMq2User(String message) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(message);
        String name = jsonNode.get("name").asText();
        String msg = jsonNode.get("msg").asText();
        System.out.println("===========" + name + "=======" + msg);

        //发送到指定用户消息
        simpMessagingTemplate.convertAndSendToUser(name, "/topic/demo", msg);
        return 0;
    }

}
