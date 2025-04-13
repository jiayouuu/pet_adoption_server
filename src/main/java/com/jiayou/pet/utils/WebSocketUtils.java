package com.jiayou.pet.utils;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtils {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketUtils(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 服务端单播消息
    public void sendUnicast(String userId, String path, String message) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/private" + path, message);
    }

    public void sendUnicast(String path, String message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenEmail = authentication.getName();
        messagingTemplate.convertAndSendToUser(tokenEmail, "/queue/private" + path, message);
    }

    // 服务端广播消息
    public void sendBroadcast(String path, String message) {
        messagingTemplate.convertAndSend("/topic/public" + path, message);
    }
}