package ru.spb.iac.model.ui;

import lombok.*;

import java.io.*;

/**
 * Created by manaev on 31.12.14.
 */
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private String time;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String content;

    public ChatMessage() {
    }

    public ChatMessage(String time, String username, String content) {
        this.time = time;
        this.username = username;
        this.content = content;
    }
}
