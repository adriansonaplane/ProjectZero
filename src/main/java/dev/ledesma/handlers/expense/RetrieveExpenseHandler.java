package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Employee;
import dev.ledesma.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class RetrieveExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.getExpenseById(id);

        if(expense != null) {
            Gson gson = new Gson();
            String json = gson.toJson(expense);
            ctx.status(201);
            ctx.result(json);
        }else{
            ctx.status(404);
            ctx.result("Could not retrieve Expense!");
        }


    }
}
