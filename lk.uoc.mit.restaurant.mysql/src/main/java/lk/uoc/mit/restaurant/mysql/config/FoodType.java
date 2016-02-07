package lk.uoc.mit.restaurant.mysql.config;

/**
 * Created by nilan on 2/7/16.
 */
public enum  FoodType {

    NO_Type("No Type"),Food("Food"),Beverage("Beverage");

    private String value;

    private FoodType(String theValue) {
        this.value = theValue;
    }

    public String getValue() {
        return this.value;
    }
}
