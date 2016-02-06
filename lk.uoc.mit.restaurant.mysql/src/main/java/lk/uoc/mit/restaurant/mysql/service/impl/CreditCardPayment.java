package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Payment;
import lk.uoc.mit.restaurant.mysql.service.PaymentInterface;

/**
 * Created by nilan on 12/26/15.
 */
public class CreditCardPayment implements PaymentInterface {

    @Override
    public String checkPaymentStatus(int orderNo) {
        return null;
    }

    @Override
    public long processPayment(Payment payment) {
        return 0;
    }
}
