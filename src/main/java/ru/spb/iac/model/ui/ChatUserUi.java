package ru.spb.iac.model.ui;

import lombok.*;

/**
 * Created by manaev on 30.12.14.
 */
public class ChatUserUi {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String username;

    private String eMail;

    private String contacts;

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public ChatUserUi() {
    }

    public ChatUserUi(String id, String username, String eMail) {
        this.id = id;
        this.username = username;
        this.eMail = eMail;
    }

    public ChatUserUi(String id, String username, String eMail, String contacts) {
        this.id = id;
        this.username = username;
        this.eMail = eMail;
        this.contacts = contacts;
    }
}
