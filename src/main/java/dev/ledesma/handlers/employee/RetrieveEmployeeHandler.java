package dev.ledesma.handlers.employee;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;


public class RetrieveEmployeeHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception{

        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.getEmployeeById(id);

        if(employee != null) {
            Gson gson = new Gson();
            String json = gson.toJson(employee);
            ctx.status(201);
            ctx.result(json);
        }else{
            ctx.status(404);
        }

    }
}
