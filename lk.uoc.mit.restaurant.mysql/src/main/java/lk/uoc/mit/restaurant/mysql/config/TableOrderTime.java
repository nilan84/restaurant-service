package lk.uoc.mit.restaurant.mysql.config;

/**
 * Created by nilan on 9/24/15.
 */
public enum TableOrderTime {
    Morning("Morning"),Day("Day"),Evening("Evening"),Night("Night");
    private String value;

    private TableOrderTime(String theValue) {
        this.value = theValue;
    }

    public String getValue() {
        return this.value;
    }
}
