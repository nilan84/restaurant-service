package lk.uoc.mit.restaurant.mysql.config;

/**
 * Created by nilan on 9/20/15.
 */
public enum PaymetType {
    Cash("Cash"),Credit_Card("Credit Card"),Mobile_Cach("Mobile Cash");

    private String value;

    private PaymetType(String theValue) {
        this.value = theValue;
    }

    public String getValue() {
        return this.value;
    }
}
