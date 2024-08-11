package org.demo.personal.springwebsockets.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.personal.springwebsockets.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class WSController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public Message receiveMessage(Message message) {
        log.info("Received message: {}", message);
        return message;
    }
    @PostMapping(value = "/api/sendMessage", consumes = "application/json")
    @ResponseBody
    public Message sendMessageToAllRest(@RequestBody Message message) {
        log.info("Sending message rst: {}", message);
        this.template.convertAndSend("/topic/messages", message);
        return message;
    }


}
