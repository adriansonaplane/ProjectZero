package dev.ledesma.handlers.expense;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class RetrieveEmployeeExpenseStatusHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));
        String status = String.valueOf(App.expenseService.getExpenseById(id).getStatus());
        Gson gson = new Gson();
        String json = gson.toJson(status);

        ctx.status(201);
        ctx.result(json);


    }
}
