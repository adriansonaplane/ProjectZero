package dev.ledesma.dao;

import dev.ledesma.entities.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface EmployeeDAO {

    boolean createEmployee(Employee employee);
    boolean deleteEmployee(int id);
    boolean updateEmployee(Employee employee);
    Employee getEmployeeById(int id);
    Set<Employee> getAllEmployees();
    boolean deleteAllEmpoyees();


}
