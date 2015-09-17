package lk.uoc.mit.restaurant.mysql.model;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by nilan on 9/14/15.
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;

    @Size(min = 3, max = 255, message="Employee Name must be at least 3 characters")
    private String empName;
    private String email;
    private String macAddress;
    private String mobNo;
    private int empId;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }


}
