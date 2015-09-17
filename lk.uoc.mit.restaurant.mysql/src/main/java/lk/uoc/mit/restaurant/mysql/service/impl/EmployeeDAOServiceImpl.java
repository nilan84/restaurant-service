package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Employee;
import lk.uoc.mit.restaurant.mysql.service.EmployeeDAOService;
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
public class EmployeeDAOServiceImpl implements EmployeeDAOService{

    @Autowired
    private DataSource dataSource;

    public List<Employee> getAllEmployee(){
        String sql = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<Employee>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Employee employee=new Employee();
            employee.setEmail(row.get("email").toString());
            employee.setEmpName(row.get("name").toString());
            employee.setMacAddress(row.get("mac_address").toString());
            employee.setMobNo(row.get("mob_no").toString());
            employee.setEmpId(Integer.parseInt(row.get("Employee_id").toString()));
            employees.add(employee);
        }

        return employees;
    }


    public long addEmployee(final Employee employee){

        final String sql="INSERT INTO Employee (email,name,mac_address,mob_no)" +
                " VALUES (?,?,?,?);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, employee.getEmail());
                ps.setString(2,employee.getEmpName());
                ps.setString(3,employee.getMacAddress());
                ps.setString(4, employee.getMobNo());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();
    }


    public long editEmployee(final Employee employee){
        final String sql="update Employee set email=?,name=?,mac_address=?,mob_no=? where Employee_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,employee.getEmail());
                ps.setString(2,employee.getEmpName());
                ps.setString(3,employee.getMacAddress());
                ps.setString(4,employee.getMobNo());
                ps.setInt(5,employee.getEmpId());
                return ps;
            }
        });
        return 1;
    }

    public Employee getEmployeeById(int empId){
        String sql = "SELECT * FROM Employee where Employee_id='"+empId+"'";
        Employee employee = new Employee();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {

            employee.setEmail(row.get("email").toString());
            employee.setEmpName(row.get("name").toString());
            employee.setMacAddress(row.get("mac_address").toString());
            employee.setMobNo(row.get("mob_no").toString());
            employee.setEmpId(Integer.parseInt(row.get("Employee_id").toString()));

        }

        return employee;
    }
}
