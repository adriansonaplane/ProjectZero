package dev.ledesma.tests.dao;

import dev.ledesma.dao.EmployeeDAO;
import dev.ledesma.dao.PostgresEmployeeDAO;
import dev.ledesma.entities.Employee;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class EmployeeDAOTest {

    static Logger logger = Logger.getLogger(EmployeeDAOTest.class.getName());

    private static EmployeeDAO empDao = new PostgresEmployeeDAO();
    private static Employee testEmployee = null;

    @Test
    @Order(1)
    void createEmployee() {
        Employee test = new Employee(1, "Adrian", "Ledesma", "CEO");
        boolean empTest = empDao.createEmployee(test);
        EmployeeDAOTest.testEmployee = test;
        System.out.print(test);
        Assertions.assertEquals(true, empTest);
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void getAllEmployees() {
    }

    @Test
    void deleteAllEmpoyees() {
    }
}