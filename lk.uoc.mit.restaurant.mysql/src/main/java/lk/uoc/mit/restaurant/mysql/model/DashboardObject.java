package lk.uoc.mit.restaurant.mysql.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by nilan on 2/5/16.
 */
public class DashboardObject implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;

    private String foodType;
    private Double foodCount;
    private Double beverageCount;
    private HashMap<String,String> fastMoveItem;
    private HashMap<String,String> showMoveItem;
    private HashMap<String,String> paymentType;

    public HashMap<String, String> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(HashMap<String, String> paymentType) {
        this.paymentType = paymentType;
    }


    public HashMap<String, String> getFastMoveItem() {
        return fastMoveItem;
    }

    public void setFastMoveItem(HashMap<String, String> fastMoveItem) {
        this.fastMoveItem = fastMoveItem;
    }

    public HashMap<String, String> getShowMoveItem() {
        return showMoveItem;
    }

    public void setShowMoveItem(HashMap<String, String> showMoveItem) {
        this.showMoveItem = showMoveItem;
    }

    public Double getBeverageCount() {
        return beverageCount;
    }

    public void setBeverageCount(Double beverageCount) {
        this.beverageCount = beverageCount;
    }

    public Double getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(Double foodCount) {
        this.foodCount = foodCount;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }



}
