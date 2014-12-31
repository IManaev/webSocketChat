package ru.spb.iac.events;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.messaging.simp.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.session.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Created by manaev on 31.12.14.
 */
@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {
    @Autowired
    @Qualifier("chatUsersRepo")
    ChatUsersRepository usersRepository;

    @Autowired private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        for (SecurityContext securityContext : lstSecurityContext)
        {
            String sessionId = ((WebAuthenticationDetails)securityContext.getAuthentication().getDetails()).getSessionId();
            if(usersRepository.contains(sessionId)){
                usersRepository.removeUser(sessionId);
            }
        }
    }

}
