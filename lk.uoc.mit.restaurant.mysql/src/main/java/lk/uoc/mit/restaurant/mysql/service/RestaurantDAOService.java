package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.GpsTracker;
import lk.uoc.mit.restaurant.mysql.model.Location;
import lk.uoc.mit.restaurant.mysql.model.Restaurant;

import java.util.List;

/**
 * Created by nilan on 7/29/15.
 */
public interface RestaurantDAOService {
    
    public List<Restaurant> getRestaurant();
    public long addLocation(Location location);
    public void editRestaurant(Restaurant restaurant);
    public Restaurant getRestaurantById(int restId);
    public GpsTracker getMaxGPS(long orderNo);

}
