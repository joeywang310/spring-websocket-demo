package com.wzc.springwebsocketdemo.send2user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wangzhicheng
 * Date: 2018-10-26
 * Time: 13:29
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private MyPrincipalHandshakeHandler myDefaultHandshakeHandler;
    @Autowired
    private AuthHandshakeInterceptor sessionAuthHandshakeInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws")
                .addInterceptors(sessionAuthHandshakeInterceptor)
                .setHandshakeHandler(myDefaultHandshakeHandler)
                .withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/");
    }



}
