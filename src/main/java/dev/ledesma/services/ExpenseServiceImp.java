package dev.ledesma.services;

import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.entities.Expense;

import java.util.Set;

public class ExpenseServiceImp implements ExpenseService {

    private ExpenseDAO expDAO;

    public ExpenseServiceImp(ExpenseDAO expDAO){

        this.expDAO = expDAO;
    }

    @Override
    public boolean createExpense(Expense expense) {

        boolean isSuccessful = this.expDAO.createExpense(expense);
        return isSuccessful;
    }

    @Override
    public boolean deleteExpense(int id) {

        boolean isSuccessful = this.expDAO.deleteExpense(id);
        return isSuccessful;
    }

    @Override
    public boolean updateExpense(Expense expense) {

        boolean isSuccessful = this.expDAO.updateExpense(expense);
        return isSuccessful;
    }

    @Override
    public boolean modifyExpense(int id) {

        boolean isSuccessful = this.expDAO.modifyExpense(id);
        return isSuccessful;
    }

    @Override
    public Expense getExpenseById(int id) {

        return this.expDAO.getExpenseById(id);
    }

    @Override
    public Set<Expense> getAllExpenses() {

        Set<Expense> expenses = this.expDAO.getAllExpenses();

        if(expenses.size() == 0){
            throw new RuntimeException("Expense Set is empty!");
        }
        return expenses;
    }

    @Override
    public Set<Expense> getAllEmployeeExpenseById(int id) {

        Set<Expense> empExpense = this.expDAO.getAllEmployeeExpenseById(id);

        if(empExpense.size() == 0){
            throw new RuntimeException("Employee Expense Set is empty!");
        }

        return empExpense;
    }

    @Override
    public boolean deleteAllEmployeeExpenseById(int id) {

        boolean isSuccessful = this.expDAO.deleteAllEmployeeExpenseById(id);
        return isSuccessful;
    }

    @Override
    public boolean deleteAllExpense() {

        boolean isSuccessful = this.expDAO.deleteAllExpense();
        return isSuccessful;
    }
}
