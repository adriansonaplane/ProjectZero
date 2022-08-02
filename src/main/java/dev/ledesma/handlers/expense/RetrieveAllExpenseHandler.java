package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class RetrieveAllExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        Set<Expense> expenseSet = new HashSet<>();
        expenseSet = App.expenseService.getAllExpenses();

        if(expenseSet.size() == 0){
            ctx.status(404);
            ctx.result("Expense Set is Empty!");
        } else{
            Gson gson = new Gson();
            String json = gson.toJson(expenseSet);
            ctx.status(202);
            ctx.result(json);
        }
    }
}
