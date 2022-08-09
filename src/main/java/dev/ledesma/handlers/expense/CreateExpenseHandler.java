package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String idString = ctx.queryParam("id");
        if(idString == null){

            String json = ctx.body();
            Gson gson = new Gson();
            Expense expense = gson.fromJson(json, Expense.class);
            Expense registeredExpense = App.expenseService.createExpense(expense);
            String expenseJson = gson.toJson(registeredExpense);
            ctx.status(201);
            ctx.result(expenseJson);

        }else if (idString != null){

            int id = Integer.parseInt(ctx.pathParam("id"));
            String json = ctx.body();
            Gson gson = new Gson();
            Expense expense = gson.fromJson(json, Expense.class);
            Expense registeredExpense = App.expenseService.createExpense(id, expense);
            String expenseJson = gson.toJson(registeredExpense);
            ctx.status(201);
            ctx.result(expenseJson);

        }else{
            ctx.status(400);
            ctx.result("Could Not Create The Expense!");
        }
    }
}
