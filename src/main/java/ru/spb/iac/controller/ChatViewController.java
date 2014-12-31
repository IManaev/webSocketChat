package ru.spb.iac.controller;

import com.google.gson.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import ru.spb.iac.security.user.*;

import javax.servlet.http.*;
import java.security.*;

/**
 * Created by manaev on 26.12.14.
 */
@Controller
public class ChatViewController {

    Gson gson = new Gson();

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/wsChatMain",method = RequestMethod.GET)
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        ModelAndView temp = new ModelAndView();
        ChatUser user = (ChatUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        temp.addObject("user",user.toUser());
        temp.addObject("contacts",gson.toJson(user.getContacts()));
        temp.setViewName("wsChatMain");
        return temp;
    }
}
