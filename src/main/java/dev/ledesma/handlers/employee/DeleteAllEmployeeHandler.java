package dev.ledesma.handlers.employee;


import dev.ledesma.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteAllEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        boolean result = App.employeeService.deleteAllEmpoyees();

        if(result){
            ctx.status(204);
        }else{
            ctx.status(400);
            ctx.result("Could not Delete All Employees");
        }
    }
}
