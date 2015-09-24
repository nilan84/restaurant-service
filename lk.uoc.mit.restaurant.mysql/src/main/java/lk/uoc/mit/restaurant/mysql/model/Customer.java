package lk.uoc.mit.restaurant.mysql.model;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by nilan on 9/4/15.
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = -7788619177798333712L;
    @Size(min = 1, max = 255, message="Customer Name Can Not be null")
    private String customerName;
    private int customerId;
    @Size(min = 1, max = 255, message="Email Can Not be null")
    private String customerEmail;
    @Size(min = 1, max = 255, message="Mob No Can Not be null")
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
