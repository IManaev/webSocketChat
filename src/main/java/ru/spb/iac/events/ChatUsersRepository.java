package ru.spb.iac.events;

import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by manaev on 31.12.14.
 */
@Service("chatUsersRepo")
public class ChatUsersRepository {
    @Getter @Setter
    private Map<String, LoginEvent> userSessions = new ConcurrentHashMap<String, LoginEvent>();

    public void addUser(String sessionId, LoginEvent event) {
        userSessions.put(sessionId, event);
    }

    public boolean contains(String id){
        return userSessions.containsKey(id);
    }

    public LoginEvent getUser(String sessionId) {
        return userSessions.get(sessionId);
    }

    public void removeUser(String sessionId) {
        userSessions.remove(sessionId);
    }

}
