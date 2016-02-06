package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.model.TableReservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by nilan on 9/24/15.
 */

@Controller
public class TableController {

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String reservations(@ModelAttribute("tableReservation") @Valid TableReservation tableReservation,Model model,HttpSession session) {
        return "home/Reservations";
    }

    @RequestMapping(value = "/reservationsadd", method = RequestMethod.POST)
    public String reservationsAdd(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("tableReservation") @Valid TableReservation tableReservation,Model model,HttpSession session,BindingResult result) {

        System.out.println("=========================");

        return "home/Reservations";
    }
}
