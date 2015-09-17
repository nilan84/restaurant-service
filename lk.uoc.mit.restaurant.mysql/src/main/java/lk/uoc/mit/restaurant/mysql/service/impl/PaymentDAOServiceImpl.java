package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.service.PaymetDAOService;
import org.springframework.stereotype.Repository;

/**
 * Created by nilan on 8/23/15.
 */
@Repository
public class PaymentDAOServiceImpl implements PaymetDAOService{
    @Override
    public double getOrderAmountByOderId(String orderId) {
        return 100;
    }
}
