package com.jiayou.pet.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 处理客户端发送的消息并广播
    @MessageMapping("/broadcast")
    @SendTo("/topic/public")
    public String broadcastMessage(String message) {
        System.out.println("收到广播消息: " + message);
        return "广播: " + message;
    }
    // 服务端单播消息
    public void sendUnicast(String userId, String message) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/private", "服务端私信: " + message);
    }
    // 服务端主动向客户端广播消息
    public void sendBroadcast(String message) {
        messagingTemplate.convertAndSend("/topic/public", "服务端广播: " + message);
    }
}
