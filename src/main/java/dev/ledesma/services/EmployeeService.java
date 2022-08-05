package dev.ledesma.services;

import dev.ledesma.entities.Employee;

import java.util.List;

public interface EmployeeService {

    boolean createEmployee(Employee employee);
    boolean deleteEmployee(int id);
    boolean updateEmployee(Employee employee);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    boolean deleteAllEmpoyees();
}
