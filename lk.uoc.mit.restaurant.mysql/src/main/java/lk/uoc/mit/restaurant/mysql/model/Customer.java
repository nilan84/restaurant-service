package lk.uoc.mit.restaurant.mysql.model;

/**
 * Created by nilan on 9/4/15.
 */
public class Customer {


    private String customerName;
    private int customerId;
    private String customerEmail;
    private String customerMob;
    private String macAddress;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMob() {
        return customerMob;
    }

    public void setCustomerMob(String customerMob) {
        this.customerMob = customerMob;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
