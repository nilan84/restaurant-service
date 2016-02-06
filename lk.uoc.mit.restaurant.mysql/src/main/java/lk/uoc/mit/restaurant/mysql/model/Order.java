package lk.uoc.mit.restaurant.mysql.model;

import lk.uoc.mit.restaurant.mysql.config.OrderStatus;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nilan on 8/23/15.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -7788619177798333712L;

    private long orderNo;
    private List<OrderFood> orderFoodList;
    private Customer customer;
    private String description;
    private OrderStatus orderStatus;
    private double orderAmount;
    private String lat;
    private String lng;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderFood> getOrderFoodList() {
        return orderFoodList;
    }

    public void setOrderFoodList(List<OrderFood> orderFoodList) {
        this.orderFoodList = orderFoodList;
    }


    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }


}
