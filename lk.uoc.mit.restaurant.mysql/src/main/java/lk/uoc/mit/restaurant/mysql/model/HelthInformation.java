package lk.uoc.mit.restaurant.mysql.model;

import java.io.Serializable;

/**
 * Created by nilan on 8/19/15.
 */
public class HelthInformation implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;


    private String barCode;
    private String discription;
    private double price;
    private String foodItemName;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }


    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }


}
