package lk.uoc.mit.restaurant.mysql.service;

/**
 * Created by nilan on 8/23/15.
 */
public interface PaymetDAOService {

    public double getOrderAmountByOderId(String orderId);
}
