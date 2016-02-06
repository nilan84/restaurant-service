package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.Payment;

/**
 * this interface use for the payment.
 */
public interface PaymentInterface {

       public String checkPaymentStatus(int orderNo);

       public long processPayment(Payment payment);


}
