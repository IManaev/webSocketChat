package ru.spb.iac.model.ui;

import lombok.*;

/**
 * Created by manaev on 30.12.14.
 */
public class ChatUserContact {
    @Getter @Setter
    private String username;

    public ChatUserContact() {
    }

    public ChatUserContact(String username) {
        this.username = username;
    }
}
