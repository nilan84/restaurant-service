package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.Employee;

import java.util.List;

/**
 * Created by nilan on 9/14/15.
 */
public interface EmployeeDAOService {

    public List<Employee> getAllEmployee();
    public long addEmployee(Employee employee);
    public long editEmployee(Employee employee);
    public Employee getEmployeeById(int empId);
}
