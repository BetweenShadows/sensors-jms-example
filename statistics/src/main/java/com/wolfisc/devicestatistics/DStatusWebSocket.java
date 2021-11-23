package com.wolfisc.devicestatistics;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class DStatusWebSocket {

    @MessageMapping("/notificacion_sensores")
    @SendTo("/topic/info_sensores")
    public String send(String msg) {
        return msg;
    }
}
