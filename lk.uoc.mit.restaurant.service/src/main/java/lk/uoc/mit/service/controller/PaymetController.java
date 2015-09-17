package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.model.Order;
import lk.uoc.mit.restaurant.mysql.service.FoodDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by nilan on 8/23/15.
 */

@Controller
public class PaymetController {
    @Autowired
    FoodDAOService foodDAOService;

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:paymetpage";
    }

    @RequestMapping(value = "/paymetpage", method = RequestMethod.GET)
    public String finalPage(Model model) {
        List<Order> orderList=foodDAOService.getAllActiveOrder();
        model.addAttribute("orderList", orderList);
        return "Payment";
    }

}
