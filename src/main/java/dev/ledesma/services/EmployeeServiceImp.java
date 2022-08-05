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
    public boolean createEmployee(Employee employee) {

        boolean isSuccesful = this.empDAO.createEmployee(employee);
        return isSuccesful;
    }

    @Override
    public boolean deleteEmployee(int id) {

        boolean isSuccessful = this.empDAO.deleteEmployee(id);
        return isSuccessful;
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        boolean isSuccessful = this.empDAO.updateEmployee(employee);
        return isSuccessful;
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

    @Override
    public boolean deleteAllEmpoyees() {

        boolean isSuccessful = this.empDAO.deleteAllEmpoyees();
        return isSuccessful;
    }
}
