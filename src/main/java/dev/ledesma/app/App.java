package dev.ledesma.app;

import com.google.gson.Gson;
import dev.ledesma.dao.EmployeeDAO;
import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.handlers.employee.*;
import dev.ledesma.handlers.expense.*;
import dev.ledesma.services.EmployeeService;
import dev.ledesma.services.EmployeeServiceImp;
import dev.ledesma.services.ExpenseService;
import dev.ledesma.services.ExpenseServiceImp;
import io.javalin.Javalin;

public class App {

    public static EmployeeService employeeService = new EmployeeServiceImp(new EmployeeDAO());
    public static ExpenseService expenseService = new ExpenseServiceImp(new ExpenseDAO());

    public static void main(String[] args) {

        Javalin app = Javalin.create();
        Gson gson = new Gson();

        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        ModifyEmployeeHandler modifyEmployeeHandler = new ModifyEmployeeHandler();
        RetrieveEmployeeHandler retrieveEmployeeHandler = new RetrieveEmployeeHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();

        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        ModifyExpenseHandler modifyExpenseHandler = new ModifyExpenseHandler();
        RetrieveExpenseHandler retrieveExpenseHandler = new RetrieveExpenseHandler();
        UpdateExpenseHandler updateExpenseHandler = new UpdateExpenseHandler();


        app.start();


    }
}
