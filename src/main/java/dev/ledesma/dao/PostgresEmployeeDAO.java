package dev.ledesma.dao;

import dev.ledesma.entities.Employee;
import org.apache.log4j.Logger;

import java.util.Set;


public class PostgresEmployeeDAO implements EmployeeDAO{

    static Logger logger = Logger.getLogger(PostgresEmployeeDAO.class.getName());
    @Override
    public boolean createEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean modifyEmployee(int id) {
        return false;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public boolean deleteAllEmpoyees() {
        return false;
    }
}
