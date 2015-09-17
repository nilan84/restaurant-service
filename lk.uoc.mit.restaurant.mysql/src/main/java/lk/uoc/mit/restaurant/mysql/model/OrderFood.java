package lk.uoc.mit.restaurant.mysql.model;

/**
 * Created by nilan on 9/4/15.
 */
public class OrderFood {
    private Food food;
    private double unitPrice;
    private int noOfitem;
    private long orderNo;
    private String description;
    private int foodId;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public int getNoOfitem() {
        return noOfitem;
    }

    public void setNoOfitem(int noOfitem) {
        this.noOfitem = noOfitem;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }


}
