package dev.ledesma.services;

import dev.ledesma.dao.EmployeeDAO;
import dev.ledesma.entities.Employee;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService{

    private EmployeeDAO empDAO;

    public EmployeeServiceImp(EmployeeDAO empDAO){
        this.empDAO = empDAO;
    }
    @Override
    public Employee createEmployee(Employee employee) {

        if(employee == null){
            throw new RuntimeException("Employee cannot be empty");
        }

        Employee savedEmployee = this.empDAO.createEmployee(employee);

        return savedEmployee;
    }

    @Override
    public boolean deleteEmployee(int id) {

        boolean isSuccessful = this.empDAO.deleteEmployee(id);
        return isSuccessful;
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        if(employee == null){
            throw new RuntimeException("Employee cannot be empty");
        }

        Employee updateEmployee = this.empDAO.updateEmployee(employee);

        return updateEmployee;
    }

    @Override
    public Employee getEmployeeById(int id) {

        return this.empDAO.getEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = this.empDAO.getAllEmployees();

        if(employees.size() == 0){
            throw new RuntimeException("Employee Set is empty!");
        }
        return employees;
    }
}
