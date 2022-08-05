package dev.ledesma.tests.utility;

import dev.ledesma.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.ledesma.utils.EmployeeCreator;
import java.util.List;

class EmployeeCreatorTest {

    @Test
    void getEmployees() {
        boolean test = false;
        EmployeeCreator employeeCreator = new EmployeeCreator();

        if(!employeeCreator.employees.isEmpty()) {
            List<Employee> employeeList = employeeCreator.getEmployees();
            for (Employee e : employeeList) {
                System.out.print(e.toString() + "\n");
            }
            test = true;
        }
        Assertions.assertTrue(test);
    }
}