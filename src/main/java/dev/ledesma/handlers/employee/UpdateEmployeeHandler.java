package dev.ledesma.handlers.employee;

import com.google.gson.Gson;
import dev.ledesma.app.App;
import dev.ledesma.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String empJson = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(empJson, Employee.class);
        Employee updatedEmp = App.employeeService.updateEmployee(employee);

        if(updatedEmp != null) {
            String json = gson.toJson(updatedEmp);
            ctx.status(201);
            ctx.result(json);
        }else{
            ctx.status(404);
            ctx.result("Could Not Update The Employee!");
        }


    }
}
