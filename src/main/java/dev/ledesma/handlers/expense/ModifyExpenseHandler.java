package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class ModifyExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String status = ctx.pathParam("status").toUpperCase();
        Expense updatedExp = App.expenseService.modifyExpense(id, Expense.expenseStatus.valueOf(status));

        if(status == "PENDING"){
            Gson gson = new Gson();
            String json = gson.toJson(updatedExp);
            ctx.status(201);
            ctx.result(json);
        } else{
            ctx.status(404);
            ctx.result("Could not Update the Expense!");
        }
    }
}
