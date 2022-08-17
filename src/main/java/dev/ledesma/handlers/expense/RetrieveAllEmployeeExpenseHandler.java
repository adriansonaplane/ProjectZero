package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RetrieveAllEmployeeExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Expense> empExpenseSet = App.expenseService.getAllEmployeeExpenseById(id);

        if(empExpenseSet.size() == 0){
            ctx.status(404);
            ctx.result("The Employee Expense Set Is Empty!");
        } else{
            Gson gson = new Gson();
            String json = gson.toJson(empExpenseSet);
            ctx.status(201);
            ctx.result(json);
        }

    }
}
