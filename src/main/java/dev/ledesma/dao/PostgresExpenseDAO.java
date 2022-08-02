package dev.ledesma.dao;

import dev.ledesma.entities.Expense;
import org.apache.log4j.Logger;

import java.util.Set;

public class PostgresExpenseDAO implements ExpenseDAO{

    static Logger logger = Logger.getLogger(PostgresExpenseDAO.class.getName());
    @Override
    public boolean createExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }

    @Override
    public boolean updateExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean modifyExpense(int id) {
        return false;
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public Set<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Set<Expense> getAllEmployeeExpenseById(int id) {
        return null;
    }

    @Override
    public boolean deleteAllEmployeeExpenseById(int id) {
        return false;
    }

    @Override
    public boolean deleteAllExpense() {
        return false;
    }
}
