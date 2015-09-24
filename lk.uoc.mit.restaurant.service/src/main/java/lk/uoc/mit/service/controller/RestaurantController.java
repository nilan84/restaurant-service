package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.model.Restaurant;
import lk.uoc.mit.restaurant.mysql.service.RestaurantDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by nilan on 9/21/15.
 */
@Controller
public class RestaurantController {
    @Autowired
    RestaurantDAOService restaurantDAOService;

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public String restaurantPage(Model model) {
        List<Restaurant> restaurantList=restaurantDAOService.getRestaurant();
        model.addAttribute("restaurants", restaurantList);
        return "admin/Restaurant";
    }

    @RequestMapping(value = "/editrestaurant", method = RequestMethod.POST)
    public String editRestaurant(@ModelAttribute("restaurant") @Valid Restaurant restaurant,Model model) {
        restaurantDAOService.editRestaurant(restaurant);
        List<Restaurant> restaurantList=restaurantDAOService.getRestaurant();
        model.addAttribute("restaurants", restaurantList);
        return "admin/Restaurant";
    }

    @RequestMapping(value = "/editrestaurantview", method = RequestMethod.GET)
    public String editRestaurantView(@RequestParam("restid") Integer restid,@ModelAttribute("restaurant") @Valid Restaurant restaurant,Model model) {
        Restaurant restaurantObject=restaurantDAOService.getRestaurantById(restid);
        model.addAttribute("restaurant", restaurantObject);
        return "admin/EditRestaurant";
    }




}
