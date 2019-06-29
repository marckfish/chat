package fr.hm.chat.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessagingController {

    @MessageMapping("/send/message")
    @SendTo("/topic/message")
    public String broadcastNews(@Payload String message) {
        System.out.println("message " +message);
        return message + " (passé par le serveur)";
    }
}
