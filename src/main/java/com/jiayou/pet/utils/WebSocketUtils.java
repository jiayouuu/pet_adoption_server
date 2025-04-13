package com.jiayou.pet.utils;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtils {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketUtils(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    // 服务端单播消息
    public void sendUnicast(String userId, String message) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/private", message);
    }
    // 服务端广播消息
    public void sendBroadcast(String message) {
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}