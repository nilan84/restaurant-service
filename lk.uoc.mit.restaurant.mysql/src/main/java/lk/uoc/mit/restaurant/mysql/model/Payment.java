package lk.uoc.mit.restaurant.mysql.model;

import lk.uoc.mit.restaurant.mysql.config.PaymetType;

/**
 * Created by nilan on 9/20/15.
 */
public class Payment {
    private Double amount;
    private PaymetType paymetType;
    private String paymentTypeString;
    private long orderNo;

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public PaymetType getPaymetType() {
        return paymetType;
    }

    public void setPaymetType(PaymetType paymetType) {
        this.paymetType = paymetType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


}
