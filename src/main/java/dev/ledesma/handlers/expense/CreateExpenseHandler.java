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

        String json = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(json, Expense.class);
        boolean result = App.expenseService.createExpense(expense);

        if(result){
            ctx.status(201);
        }else{
            ctx.status(400);
            ctx.result("Coud not Create the Expense");
        }

    }
}
