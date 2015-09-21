package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.Customer;

import java.util.List;

/**
 * Created by nilan on 9/14/15.
 */
public interface CustomerDAOService {

    public List<Customer> getAllCustomer();
    public long addCustomer(Customer customer);
    public long editCustomer(Customer customer);
    public Customer getCustomerById(int customerId);
    public Customer getCustomerByMAC(String macAddress);
}
