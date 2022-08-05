package dev.ledesma.handlers.employee;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RetrieveAllEmpoyeeHandler implements Handler {


    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        List<Employee> employeeSet = App.employeeService.getAllEmployees();

        if(employeeSet.size() == 0){
            ctx.status(404);
            ctx.result("Employee Set is empty!");
        } else{
            Gson gson = new Gson();
            String json = gson.toJson(employeeSet);
            ctx.status(201);
            ctx.result(json);
        }

    }
}
