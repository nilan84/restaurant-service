package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.Location;
import lk.uoc.mit.restaurant.mysql.model.User;

import java.util.List;

/**
 * Created by nilan on 7/29/15.
 */
public interface RestaurantDAOService {
    
    public List<User> getRestaurant();
    public long addLocation(Location location);
}
