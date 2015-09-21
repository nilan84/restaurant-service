package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.Payment;

/**
 * Created by nilan on 8/23/15.
 */
public interface PaymetDAOService {

    public double getOrderAmountByOderId(String orderId);
    public long addPaymet(Payment payment);
}
