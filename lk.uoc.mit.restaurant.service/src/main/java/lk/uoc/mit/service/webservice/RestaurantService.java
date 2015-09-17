package lk.uoc.mit.service.webservice;

import lk.uoc.mit.restaurant.mysql.model.Location;
import lk.uoc.mit.restaurant.mysql.service.RestaurantDAOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by nilan on 7/28/15.
 */
@Controller
public class RestaurantService {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    //http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program
    @Autowired
    RestaurantDAOService restaurantDAOService;//=new RestaurantDAOServiceImpl();

    @RequestMapping(value = "/restaurant/get", method = RequestMethod.GET)
    public
    @ResponseBody
    String getRestaurant() {
        System.out.println("tes1t");
        logger.info("Start getAllRestaurant.");
        restaurantDAOService.getRestaurant();

        return "Restaurant 1";

    }


    @RequestMapping(value = "/location/add", method = RequestMethod.POST)
    public
    @ResponseBody
    String updateLocation(@RequestBody Location location) {
        logger.info("Start getAllRestaurant.");
        restaurantDAOService.addLocation(location);
        return "ok";

    }


}