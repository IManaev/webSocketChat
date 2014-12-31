package ru.spb.iac.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import ru.spb.iac.events.*;
import ru.spb.iac.model.ui.*;

import java.security.*;
import java.text.*;
import java.util.*;

/**
 * Created by manaev on 31.12.14.
 */
@Controller
public class ChatMessagingController {
    private SimpleDateFormat sdfTime2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * Handler to add one stock
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @MessageMapping("/message")
    public void sendGlobalMessage(ChatMessage message,Principal principal) throws Exception {
        message.setTime(sdfTime2.format(new Date()));
        template.convertAndSend("/chat/messages",message);
    }

}
