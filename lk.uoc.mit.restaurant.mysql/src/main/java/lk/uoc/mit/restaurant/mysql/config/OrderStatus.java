package lk.uoc.mit.restaurant.mysql.config;

/**
 * Created by nilan on 9/13/15.
 */
public enum OrderStatus {
    Pending("Pending"),Confirm("Confirm"),Delivering("Delivering"),Delivered("Delivered"),Payment_Done("Payment Done");
    private String value;

    private OrderStatus(String theValue) {
        this.value = theValue;
    }

    public String getValue() {
        return this.value;
    }
}
