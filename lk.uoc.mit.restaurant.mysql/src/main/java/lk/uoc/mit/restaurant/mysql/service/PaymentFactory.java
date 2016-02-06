package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.config.PaymetType;
import lk.uoc.mit.restaurant.mysql.service.impl.CashPayment;
import lk.uoc.mit.restaurant.mysql.service.impl.CreditCardPayment;
import lk.uoc.mit.restaurant.mysql.service.impl.MobilePayment;

/**
 * Payment Factory method for any type of payment .
 *
 */
public class PaymentFactory {

    public PaymentInterface getPaymentObject(PaymetType paymetType){

        if(paymetType==PaymetType.Cash){

            return new CashPayment();
        }
        else if(paymetType==PaymetType.Visa_Card){

            return new CreditCardPayment();

        }
        else if(paymetType==PaymetType.Mobile_Cach){

            return new CreditCardPayment();

        }
        else if(paymetType==PaymetType.Mobile_Cach){

            return new MobilePayment();

        }
        return  null;
    }
}
