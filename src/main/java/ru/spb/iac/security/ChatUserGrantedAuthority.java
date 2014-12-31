package ru.spb.iac.security;

import org.springframework.security.core.*;

/**
 * Created by manaev on 25.12.14.
 */
public class ChatUserGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -3786297951121082644L;

    private String authority = null;
    public ChatUserGrantedAuthority(String auth) { authority = auth; }
    @Override
    public String getAuthority() {
        return authority;
    }
}
