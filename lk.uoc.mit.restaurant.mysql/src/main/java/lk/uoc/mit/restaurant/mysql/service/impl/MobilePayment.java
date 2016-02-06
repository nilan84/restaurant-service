package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Payment;
import lk.uoc.mit.restaurant.mysql.service.PaymentInterface;

/**
 * Mobile Payment.
 */
public class MobilePayment implements PaymentInterface{
    @Override
    public String checkPaymentStatus(int orderNo) {
        return null;
    }

    @Override
    public long processPayment(Payment payment) {
        return 0;
    }
}
