package lk.uoc.mit.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by nilan on 9/24/15.
 */

@Controller
public class TableController {

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String reservations(Model model,HttpSession session) {
        return "home/Reservations";
    }
}
