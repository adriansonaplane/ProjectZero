package dev.ledesma.handlers.expense;

import dev.ledesma.app.App;
import dev.ledesma.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.getExpenseById(id);
        boolean result = App.expenseService.deleteExpense(id);

        if (result){
            ctx.status(204);
            ctx.result("Expense Deleted!");
        }else if (expense.getStatus() == Expense.expenseStatus.APPROVED){
            ctx.status(422);
            ctx.result("Could Not Delete Approved Expense!");
        }else if (expense.getStatus() == Expense.expenseStatus.DENIED) {
            ctx.status(422);
            ctx.result("Could Not Delete Denied Expense!");
        }else if (!result && expense == null){
            ctx.status(404);
            ctx.result("Could Not Find Expense!");
        }else{
            ctx.status(400);
            ctx.result("Could Not Delete The Expense!");
        }
    }
}
