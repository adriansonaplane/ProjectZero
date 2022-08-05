package dev.ledesma.services;

import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.entities.Expense;

import java.util.List;

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
    public boolean modifyExpense(int id, Expense.expenseStatus status) {

        boolean isSuccessful = this.expDAO.modifyExpense(id, status);
        return isSuccessful;
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
    public List<Expense> getAllEmployeeExpenseByStatus(Expense.expenseStatus status) {

        List<Expense> empExpense = this.expDAO.getAllExpenseByStatus(status);

        if(empExpense.size() == 0){
            throw new RuntimeException("Employee Expense Set is empty!");
        }

        return empExpense;
    }

}
