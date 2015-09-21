package lk.uoc.mit.restaurant.mysql.config;

/**
 * Created by nilan on 9/20/15.
 */
public enum UserType {
    Mobile("Mobile"),
    Web("web"),
    Admin("Admin"),
    Waiter("Waiter");

    private String value;

    private UserType(String theValue) {
        this.value = theValue;
    }

    public String getValue() {
        return this.value;
    }
}
