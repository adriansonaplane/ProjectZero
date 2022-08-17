package dev.ledesma.app;


import dev.ledesma.dao.PostgresEmployeeDAO;
import dev.ledesma.dao.PostgresExpenseDAO;
import dev.ledesma.handlers.employee.*;
import dev.ledesma.handlers.expense.*;
import dev.ledesma.services.EmployeeService;
import dev.ledesma.services.EmployeeServiceImp;
import dev.ledesma.services.ExpenseService;
import dev.ledesma.services.ExpenseServiceImp;
import dev.ledesma.utils.EmployeeCreator;
import dev.ledesma.utils.ExpenseCreator;
import io.javalin.Javalin;


public class App {

    public static EmployeeService employeeService = new EmployeeServiceImp(new PostgresEmployeeDAO());
    public static ExpenseService expenseService = new ExpenseServiceImp(new PostgresExpenseDAO());

    public static void main(String[] args) {

        Javalin app = Javalin.create();

        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        RetrieveAllEmpoyeeHandler retrieveAllEmployeeHandler = new RetrieveAllEmpoyeeHandler();
        RetrieveEmployeeHandler retrieveEmployeeHandler = new RetrieveEmployeeHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();

        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        ModifyExpenseHandler modifyExpenseHandler = new ModifyExpenseHandler();
        RetrieveAllEmployeeExpenseHandler retrieveAllEmployeeExpenseHandler = new RetrieveAllEmployeeExpenseHandler();
        RetrieveAllExpenseHandler retrieveAllExpenseHandler = new RetrieveAllExpenseHandler();
        RetrieveExpenseHandler retrieveExpenseHandler = new RetrieveExpenseHandler();
        UpdateExpenseHandler updateExpenseHandler = new UpdateExpenseHandler();

        //Employee
        //POST /employees
        app.post("/employee", createEmployeeHandler);
        //GET /employees
        app.get("/employee", retrieveAllEmployeeHandler);
        //GET /employees/120
        app.get("/employee/{id}", retrieveEmployeeHandler);
        //PUT employees/150
        app.put("/employee/{id}", updateEmployeeHandler);
        //DELETE /employees/190
        app.delete("/employee/{id}", deleteEmployeeHandler);

        //Expense
        //POST /expenses
        app.post("/expense", createExpenseHandler);
        //GET /expenses
        app.get("/expense", retrieveAllExpenseHandler);
        //GET /expenses?status=pending
//        app.get("/expense?status=pending", retrieveAllExpenseHandler);
        //GET /expenses/12
        app.get("/expense/{id}", retrieveExpenseHandler);
        //PUT /expenses/15
        app.put("/expense/{id}", updateExpenseHandler);
        //PATCH /expenses/20/approve
        //app.patch("/expense/{id}/{status}", modifyExpenseHandler);
        //PATCH /expenses/20/deny
        app.patch("/expense/{id}/{status}", modifyExpenseHandler);
        //DELETE /expenses/19
        app.delete("/expense/{id}", deleteExpenseHandler);
        //Nested
        //GET /employee/120/expense
        app.get("/employee/{id}/expense", retrieveAllEmployeeExpenseHandler);
        //POST /employee/120/expense
        app.post("/employee/{id}/expense", createExpenseHandler);

        EmployeeCreator employeeCreator = new EmployeeCreator();
        ExpenseCreator expenseCreator = new ExpenseCreator();
//        employeeCreator.createEmployeeTable();
//        expenseCreator.createExpenseTable();


        app.start();
    }
}
