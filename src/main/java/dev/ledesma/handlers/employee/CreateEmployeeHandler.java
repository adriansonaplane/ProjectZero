package dev.ledesma.handlers.employee;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        Employee registeredEmployee = App.employeeService.createEmployee(employee);
        String employeeJson = gson.toJson(registeredEmployee);

        if(registeredEmployee != null){
            ctx.status(201);
            ctx.result(employeeJson);
        }else{
            ctx.status(400);
            ctx.result("Could Not Create The Employee!");
        }

    }
}
