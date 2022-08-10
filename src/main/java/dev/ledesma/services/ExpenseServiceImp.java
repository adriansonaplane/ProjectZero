package dev.ledesma.services;

import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.entities.Expense;
import dev.ledesma.entities.ExpenseStatus;

import java.util.List;

public class ExpenseServiceImp implements ExpenseService {

    private ExpenseDAO expDAO;

    public ExpenseServiceImp(ExpenseDAO expDAO){

        this.expDAO = expDAO;
    }

    @Override
    public Expense createExpense(Expense expense) {

        if(expense == null){
            throw new RuntimeException("Expense cannot be empty");
        }

        Expense savedExpense = this.expDAO.createExpense(expense);

        return savedExpense;
    }
    public Expense createExpense(int id, Expense expense) {

        if(expense == null){
            throw new RuntimeException("Expense cannot be empty");
        }

        Expense savedExpense = this.expDAO.createExpense(expense);

        return savedExpense;
    }
    @Override
    public boolean deleteExpense(int id) {

        boolean isSuccessful = this.expDAO.deleteExpense(id);
        return isSuccessful;
    }

    @Override
    public Expense updateExpense(Expense expense) {

        if(expense == null){
            throw new RuntimeException("Employee cannot be empty");
        }

        Expense updateExpense = this.expDAO.updateExpense(expense);

        return updateExpense;
    }

    @Override
    public Expense modifyExpense(int id, ExpenseStatus status) {

        Expense updatedExpense = this.expDAO.modifyExpense(id, status);

        return updatedExpense;
    }

    @Override
    public Expense getExpenseById(int id) {

        return this.expDAO.getExpenseById(id);
    }

    @Override
    public List<Expense> getAllExpenses() {

        List<Expense> expenses = this.expDAO.getAllExpenses();

        if(expenses.size() == 0){
            throw new RuntimeException("Expense Set is empty!");
        }
        return expenses;
    }

    @Override
    public List<Expense> getAllEmployeeExpenseById(int id) {

        List<Expense> empExpense = this.expDAO.getAllEmployeeExpenseById(id);

        if(empExpense.size() == 0){
            throw new RuntimeException("Employee Expense Set is empty!");
        }

        return empExpense;
    }

    @Override
    public List<Expense> getAllEmployeeExpenseByStatus(ExpenseStatus status) {

        List<Expense> empExpense = this.expDAO.getAllExpenseByStatus(status);

        if(empExpense.size() == 0){
            throw new RuntimeException("Employee Expense Set is empty!");
        }

        return empExpense;
    }

}
