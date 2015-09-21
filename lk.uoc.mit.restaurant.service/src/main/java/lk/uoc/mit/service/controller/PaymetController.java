package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.config.OrderStatus;
import lk.uoc.mit.restaurant.mysql.model.Order;
import lk.uoc.mit.restaurant.mysql.model.Payment;
import lk.uoc.mit.restaurant.mysql.service.FoodDAOService;
import lk.uoc.mit.restaurant.mysql.service.PaymetDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 8/23/15.
 */

@Controller
public class PaymetController {
    @Autowired
    FoodDAOService foodDAOService;
    @Autowired
    PaymetDAOService paymetDAOService;

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


    @RequestMapping(value = "/addpayment", method = RequestMethod.POST)
    public String addPaymet(@ModelAttribute("payment") @Valid Payment payment,HttpSession session,Map<String, Object> model) {
            paymetDAOService.addPaymet(payment);
            foodDAOService.changeOrderStatus(payment.getOrderNo(),OrderStatus.Payment_Done);
            return "Payment";
    }

}
