package dev.ledesma.tests.dao;

import dev.ledesma.dao.EmployeeDAO;
import dev.ledesma.dao.PostgresEmployeeDAO;
import dev.ledesma.entities.Employee;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class EmployeeDAOTest {

    static Logger logger = Logger.getLogger(EmployeeDAOTest.class.getName());

    private static EmployeeDAO empDao = new PostgresEmployeeDAO();
    private static Employee testEmployee = null;

    @Test
    void createEmployee() {
        Employee test = new Employee(1, "Adrian", "Ledesma", "CEO");
        boolean empTest = empDao.createEmployee(test);
        EmployeeDAOTest.testEmployee = test;
        System.out.print(test);
        Assertions.assertTrue(empTest);
    }

    @Test
    void deleteEmployee() {
        Assertions.assertTrue(empDao.deleteEmployee(1));
    }

    @Test
    void updateEmployee() {
        Employee test = new Employee(1, "Adrian", "Ledesman", "CFO");
        boolean empTest = empDao.updateEmployee(test);
        Assertions.assertTrue(empTest);
    }

    @Test
    void getEmployeeById() {
        Employee testEmp = new Employee();
        testEmp = empDao.getEmployeeById(2);
        System.out.print(testEmp);
        Assertions.assertInstanceOf(Employee.class, testEmp);
    }

    @Test
    void getAllEmployees() {
        Set<Employee> empSet = new HashSet<>();
        empSet = empDao.getAllEmployees();
        Employee test = new Employee(3, "Adrian", "Ledesma", "CEO");
        System.out.println(empSet);
        Assertions.assertTrue(empSet.contains(test));
    }

    @Test
    void deleteAllEmpoyees() {
        Assertions.assertTrue(empDao.deleteAllEmpoyees());
    }
}