package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String expJson = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(expJson, Expense.class);
        Expense updatedExp = App.expenseService.updateExpense(expense);

        if (updatedExp != null) {
            String json = gson.toJson(updatedExp);
            ctx.status(201);
            ctx.result(json);
        }else{
            ctx.status(404);
            ctx.result("Could not Update the Expense!");
        }
    }
}
