package dev.ledesma.services;

import dev.ledesma.entities.Expense;

import java.util.Set;

public interface ExpenseService {

    boolean createExpense(Expense expense);
    boolean deleteExpense(int id);
    boolean updateExpense(Expense expense);
    boolean modifyExpense(int id);
    Expense getExpenseById(int id);
    Set<Expense> getAllExpenses();
    Set<Expense> getAllEmployeeExpenseById(int id);
    Set<Expense> getAllEmployeeExpenseByStatus(int id);
}
