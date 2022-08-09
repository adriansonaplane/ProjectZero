package dev.ledesma.services;

import dev.ledesma.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    boolean deleteEmployee(int id);
    Employee updateEmployee(Employee employee);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
}
