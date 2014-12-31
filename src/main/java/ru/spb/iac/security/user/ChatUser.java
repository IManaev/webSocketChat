package ru.spb.iac.security.user;


import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import ru.spb.iac.model.ui.*;
import ru.spb.iac.security.validation.*;

import javax.validation.constraints.*;
import java.io.*;
import java.util.*;

/**
 * Created by manaev on 25.12.14.
 */
@Document(collection = ChatUser.COLLECTION_NAME)
public class ChatUser implements Serializable, UserDetails {
    public static final String COLLECTION_NAME = "users";
    private static final long serialVersionUID = 1201392234549297485L;

    @Getter @Setter
    @Id
    private String id;

    @Setter
    @NotNull
    @Size(min=4,max=12)
    private String password;

    @Setter
    @NotNull
    @Size(min=3,max=20)
    @ValidUName
    private String username;

    @NotNull
    @ValidEmail
    private String eMail;

    private List<ChatUserUi> contacts;

    private GrantedAuthority[] authorities = null;

    public void setAuthorities(GrantedAuthority[] authorities) {
        this.authorities = authorities;
    }

    public ChatUser() {
    }

    public ChatUser(String password, String username, GrantedAuthority[] authorities) {
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        Collections.addAll(auth, authorities);
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ChatUserUi toUser(){
        return new ChatUserUi(id,username,eMail);
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<ChatUserUi> getContacts() {
        if(contacts == null){
            contacts = new ArrayList<ChatUserUi>();
        }
        return contacts;
    }

    public void setContacts(List<ChatUserUi> contacts) {
        this.contacts = contacts;
    }
}
