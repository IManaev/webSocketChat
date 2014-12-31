package ru.spb.iac.controller;

import lombok.extern.log4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by manaev on 25.12.14.
 */
@Controller
@RequestMapping("/ajax")
@Log4j
public class AjaxController {
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String getMongoInfo() {
          return "fucking ajax is on";
    }

    public AjaxController() {
        log.info("Controller is on");
    }
}
