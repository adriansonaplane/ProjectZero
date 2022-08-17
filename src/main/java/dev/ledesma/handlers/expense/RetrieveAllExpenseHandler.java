package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Expense;
import dev.ledesma.entities.ExpenseStatus;
import dev.ledesma.utils.ExpenseCreator;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RetrieveAllExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {



        List<Expense> expenseSet = new ArrayList<>();
        if (ctx.queryParam("status") == null){
            expenseSet = App.expenseService.getAllExpenses();
            Gson gson = new Gson();
            String json = gson.toJson(expenseSet);
            ctx.status(201);
            ctx.result(json);
        } else if (ctx.queryParam("status").toUpperCase().equals("APPROVED")) {
            expenseSet = App.expenseService.getAllEmployeeExpenseByStatus(ExpenseStatus.APPROVED);
            Gson gson = new Gson();
            String json = gson.toJson(expenseSet);
            ctx.status(201);
            ctx.result(json);
        } else if (ctx.queryParam("status").toUpperCase().equals("DENIED")) {
            expenseSet = App.expenseService.getAllEmployeeExpenseByStatus(ExpenseStatus.DENIED);
            Gson gson = new Gson();
            String json = gson.toJson(expenseSet);
            ctx.status(201);
            ctx.result(json);
        } else if (ctx.queryParam("status").toUpperCase().equals("PENDING")) {
            expenseSet = App.expenseService.getAllEmployeeExpenseByStatus(ExpenseStatus.PENDING);
            Gson gson = new Gson();
            String json = gson.toJson(expenseSet);
            ctx.status(201);
            ctx.result(json);
        }else{
            ctx.status(404);
            ctx.result("Cannot Retrieve Expenses!");
        }

    }
}
