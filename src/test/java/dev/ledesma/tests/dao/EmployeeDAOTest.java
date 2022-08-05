package dev.ledesma.tests.dao;

import dev.ledesma.dao.EmployeeDAO;
import dev.ledesma.dao.PostgresEmployeeDAO;
import dev.ledesma.entities.Employee;
import dev.ledesma.utils.EmployeeCreator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class EmployeeDAOTest {

    static Logger logger = Logger.getLogger(EmployeeDAOTest.class.getName());

    private static EmployeeDAO empDao = new PostgresEmployeeDAO();
    private static EmployeeCreator employeeCreator = new EmployeeCreator();
    //Creates 1-100 New Employees
    private static int originalSize = 0;

    @Test
    @Order(1)
    void createEmployee() {

        boolean test = false;

        System.out.println("\nCreating Employees...");
        for(Employee e: employeeCreator.employees) {

            if(!empDao.createEmployee(e)){
                test = false;
                System.out.println("Could Not Create Employee\n");
                break;
            }

            System.out.println(e);
            test = true;
        }

        originalSize = employeeCreator.employees.size();
        System.out.println("Created " + employeeCreator.employees.size() + " Employees\n");
        Assertions.assertTrue(test);
    }

    @Test
    @Order(2)
    void deleteEmployee() {

        int c =0;
        int size = 0;
        int id = 0;
        boolean test = false;

        Set<Integer> idSet = new HashSet<>();
        Random r = new Random();

        for(Employee e: empDao.getAllEmployees()){
            idSet.add(e.getId());
        }

        while(size == 0 || size == originalSize){
            size = r.nextInt(originalSize);
        }

        System.out.println("Deleting " + size + " Employees...");

        for(int i = 0; i < size; i++) {

            while (!idSet.contains(id)) {
                id = r.nextInt(originalSize) + 1;
            }
            if (idSet.contains(id)) {

                System.out.println(empDao.getEmployeeById(id));

                if (empDao.deleteEmployee(id)) {
                    idSet.remove(id);
                    test = true;
                    c++;
                }else{
                    test = false;
                    System.out.println("Could not Delete: " + empDao.getEmployeeById(id));
                    break;
                }

            }
        }
        System.out.println("Deleted " + c +" Employees\n");
        Assertions.assertTrue(test);
    }

    @Test
    @Order(3)
    void updateEmployee() {

        int c = 0;
        int size = 0;
        int id = 0;
        int firstSize = employeeCreator.firstNames.length;
        int lastSize = employeeCreator.lastNames.length;
        int titleSize = employeeCreator.title.length;
        boolean test = false;

        Random r = new Random();

        Set<Integer> idSet = new HashSet<>();
        for (Employee e : empDao.getAllEmployees()) {
            idSet.add(e.getId());

        }

        while(size > empDao.getAllEmployees().size() || size == 0){
            size = r.nextInt(originalSize);
        }
        System.out.println("Updating " + size + " Employees...");

        for(int i = 0; i < size; i ++) {

            id = r.nextInt(originalSize);
            System.out.println("Trying Id... " + id + " of " + originalSize);

            while (!idSet.contains(id)) {
                id = r.nextInt(originalSize) + 1;
                System.out.println("Trying Id... " + id + " of " + originalSize);
            }

            System.out.println("Original: " + empDao.getEmployeeById(id));

            Employee updated = new Employee();

            updated.setId(empDao.getEmployeeById(id).getId());
            updated.setFirstName(
                    employeeCreator.firstNames[
                            r.nextInt(firstSize)
                            ]
            );
            updated.setLastName(
                    employeeCreator.firstNames[
                            r.nextInt(lastSize)
                            ]
            );
            updated.setTitle(
                    employeeCreator.title[
                            r.nextInt(titleSize)
                            ]
            );
            if (empDao.updateEmployee(updated)) {

                test = true;
                System.out.println("Updated: " + empDao.getEmployeeById(id));
                c++;

            } else {
                System.out.println("Failed to Update: " + empDao.getEmployeeById(id));
                test = false;
            }
        }
//        System.out.println("Updating Employees...");
//        for(Employee e: empDao.getAllEmployees()){
//
//            if(r.nextInt(10) > r.nextInt(10)) {
//
//                System.out.println("Original: " + e);
//                Employee updated = new Employee();
//                updated.setId(e.getId());
//
//                updated.setFirstName(
//                        employeeCreator.firstNames[
//                                r.nextInt(firstSize)
//                                ]
//                );
//                updated.setLastName(
//                        employeeCreator.firstNames[
//                                r.nextInt(lastSize)
//                                ]
//                );
//                updated.setTitle(
//                        employeeCreator.title[
//                                r.nextInt(titleSize)
//                                ]
//                );
//                if(empDao.updateEmployee(updated)){
//
//                    test = true;
//                    System.out.println("Updated: " + empDao.getEmployeeById(e.getId()));
//                    c++;
//
//                }else{
//
//                    System.out.println("Failed to Update: " + e);
//                    test = false;
//                    break;
//
//                }
//            }
//            test = true;
//        }
        System.out.println("Updated " + c + " Employees\n");
        Assertions.assertTrue(test);
    }

    @Test
    @Order(4)
    void getEmployeeById() {
        int id = 0;
        boolean test = false;

        Set<Integer> idSet = new HashSet<>();
        Random r = new Random();

        for(Employee e: empDao.getAllEmployees()){
            idSet.add(e.getId());

        }

        id = r.nextInt(originalSize);

        System.out.println("Trying Id... " + id + " of " + originalSize);

        while(!idSet.contains(id)){

            id = r.nextInt(originalSize) + 1;
            System.out.println("Trying Id... " + id + " of " + originalSize);

        }
        if(empDao.getEmployeeById(id) != null){

            System.out.println("Retrieved Employee: " + empDao.getEmployeeById(id) + "\n");
            test = true;

        }else{

            test = false;

        }

        Assertions.assertTrue(test);

    }

    @Test
    @Order(5)
    void getAllEmployees() {

        boolean test = false;
        int c =0;

        if(!empDao.getAllEmployees().isEmpty()) {

            System.out.println("Retrieving All Employees..");
            for (Employee e : empDao.getAllEmployees()) {System.out.println(e); c++;}
            test = true;

        }else{System.out.println("Employees is empty");}

        System.out.println("Retrieved " + c + " Employees\n");
        Assertions.assertTrue(test);
    }

    @Test
    @Order(6)
    void deleteAllEmpoyees() {

        boolean test = false;

        System.out.println("Deleting All Employees...");

        if(empDao.deleteAllEmpoyees()){
            test = true;
            System.out.println("Deleted All Employees\n");
        }
        Assertions.assertTrue(test);
    }
}