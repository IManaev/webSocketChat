package ru.spb.iac.controller;

import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.*;
import ru.spb.iac.model.ui.*;
import ru.spb.iac.security.enc.*;
import ru.spb.iac.security.user.*;
import ru.spb.iac.service.mongo.*;

import javax.servlet.http.*;
import javax.validation.*;
import java.util.*;

/**
 * Created by manaev on 26.12.14.
 */
@Log4j
@Controller
public class ChatSecurityController {
    @Autowired
    @Qualifier("commonMongoService")
    private CommonMongoService mongoService;

    @Value("${mongo.db}")
    private String mongoDbName;

    @Autowired
    @Qualifier("encrypter")
    BCryptHash hash;

    @RequestMapping(value = "/login_user", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", "You've been logged out successfully.");
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value = "/login_error", method = RequestMethod.GET)
    public ModelAndView login_error() {
        ModelAndView model = new ModelAndView();
        model.addObject("error", "Invalid username and password!");
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView general() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        log.debug("Rendering registration page.");

        model.addAttribute("user",new ChatUser());
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid @ModelAttribute("user") ChatUser user, BindingResult result, WebRequest request)
    {
        if (result.hasErrors()) {
            ModelAndView temp = new ModelAndView();
            temp.addObject("error", result.getAllErrors());
            temp.addObject("user", user);
            temp.setViewName("register");
            return temp;
        } else{
            ModelAndView model = new ModelAndView();
            model.setViewName("login");
            user.setPassword(hash.getEncPassword(user.getPassword()));
            GrantedAuthority[] authorities = new GrantedAuthority[1];
            authorities[0] = new SimpleGrantedAuthority("ROLE_USER");
            user.setAuthorities(authorities);
            List<ChatUserUi> cont = new ArrayList<ChatUserUi>();
            cont.add(new ChatUserUi("test1","test1@test.ru"));
            cont.add(new ChatUserUi("test2","test2@test.ru"));
            user.setContacts(cont);
            mongoService.getMongoOperator(mongoDbName).save(user);
            return model;
        }
    }

    private boolean busyNick(String name){
        return false;
    }
}
