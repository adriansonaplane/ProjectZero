package dev.ledesma.app;

import com.google.gson.Gson;
import dev.ledesma.dao.EmployeeDAO;
import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.dao.PostgresEmployeeDAO;
import dev.ledesma.dao.PostgresExpenseDAO;
import dev.ledesma.entities.Employee;
import dev.ledesma.handlers.employee.*;
import dev.ledesma.handlers.expense.*;
import dev.ledesma.services.EmployeeService;
import dev.ledesma.services.EmployeeServiceImp;
import dev.ledesma.services.ExpenseService;
import dev.ledesma.services.ExpenseServiceImp;
import io.javalin.Javalin;

public class App {

    public static EmployeeService employeeService = new EmployeeServiceImp(new PostgresEmployeeDAO());
    public static ExpenseService expenseService = new ExpenseServiceImp(new PostgresExpenseDAO());

    public static void main(String[] args) {

        Javalin app = Javalin.create();


        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();

        app.post("/employee", createEmployeeHandler);
        app.start();


    }
}
