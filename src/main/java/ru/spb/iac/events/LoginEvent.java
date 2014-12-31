package ru.spb.iac.events;

import lombok.*;
import ru.spb.iac.model.ui.*;

/**
 * Created by manaev on 31.12.14.
 */
public class LoginEvent {

    @Getter @Setter
    private ChatUserUi user;

    @Getter @Setter
    private long timestamp;

    public LoginEvent() {

    }

    public LoginEvent(ChatUserUi user, long timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }
}
