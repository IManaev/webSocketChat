package ru.spb.iac.security;

import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;
import ru.spb.iac.events.*;
import ru.spb.iac.security.user.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * Created by manaev on 25.12.14.
 */
@Log4j
@Service("chatAuthenticationSuccessHandler")
public class ChatAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    @Qualifier("chatUsersRepo")
    ChatUsersRepository usersRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RedirectStrategy redirect = new DefaultRedirectStrategy();

    @Autowired private SimpMessagingTemplate template;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(request, httpServletResponse, authentication);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(300);
        }
        LoginEvent event = new LoginEvent();
        event.setTimestamp(session.getCreationTime());
        event.setUser(((ChatUser) authentication.getPrincipal()).toUser());
        usersRepository.addUser(((WebAuthenticationDetails)authentication.getDetails()).getSessionId(),event);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            }
        }
        if (isUser) {
           return "/wsChatMain";   //вернуть домашнюю страничку пользователя
        } else {
            throw new IllegalStateException();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
