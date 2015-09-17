package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Customer;
import lk.uoc.mit.restaurant.mysql.service.CustomerDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 9/14/15.
 */
@Repository
public class CustomerDAOServiceImpl implements CustomerDAOService{

    @Autowired
    private DataSource dataSource;


    @Override
    public List<Customer> getAllCustomer() {
        String sql = "SELECT * FROM Customer";
        List<Customer> customers = new ArrayList<Customer>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Customer customer=new Customer();
            customer.setCustomerId(Integer.parseInt(row.get("Customer_id").toString()));
            customer.setCustomerEmail(row.get("email").toString());
            customer.setCustomerName(row.get("cus_name").toString());
            customer.setCustomerMob(row.get("mob_no").toString());
            customer.setMacAddress(row.get("mac_address").toString());
            customers.add(customer);
        }

        return customers;
    }

    @Override
    public long addCustomer(final Customer customer) {
        final String sql="INSERT INTO Customer (email,cus_name,mob_no,mac_address)" +
                " VALUES (?,?,?,?);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customer.getCustomerEmail());
                ps.setString(2, customer.getCustomerName());
                ps.setString(3,customer.getCustomerMob());
                ps.setString(4, customer.getMacAddress());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public long editCustomer(final Customer customer) {
        final String sql="update Customer set email=?,cus_name=?,mob_no=?,mac_address=? where Customer_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1, customer.getCustomerEmail());
                ps.setString(2,customer.getCustomerName());
                ps.setString(3,customer.getCustomerMob());
                ps.setString(4, customer.getMacAddress());
                ps.setInt(5,customer.getCustomerId());
                return ps;
            }
        });
        return 1;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM Customer where Customer_id='"+customerId+"'";
        Customer customer = new Customer();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            customer.setCustomerId(Integer.parseInt(row.get("Customer_id").toString()));
            customer.setCustomerEmail(row.get("email").toString());
            customer.setCustomerName(row.get("cus_name").toString());
            customer.setCustomerMob(row.get("mob_no").toString());
            customer.setMacAddress(row.get("mac_address").toString());
        }

        return customer;
    }
}
