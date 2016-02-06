package lk.uoc.mit.restaurant.mysql.service.impl;


import lk.uoc.mit.restaurant.mysql.model.Payment;
import lk.uoc.mit.restaurant.mysql.service.PaymentInterface;
import lk.uoc.mit.restaurant.mysql.service.PaymetDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *Cash Payment
 */
@Repository
public class CashPayment implements PaymentInterface {
    @Autowired
    PaymetDAOService paymetDAOService;

    @Override
    public String checkPaymentStatus(int orderNo) {
        return null;
    }

    @Override
    public long processPayment(Payment payment) {
        return paymetDAOService.addPaymet(payment);
    }
}
