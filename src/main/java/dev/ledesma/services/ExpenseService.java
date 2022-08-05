package dev.ledesma.services;

import dev.ledesma.entities.Expense;

import java.util.List;

public interface ExpenseService {

    boolean createExpense(Expense expense);
    boolean deleteExpense(int id);
    boolean updateExpense(Expense expense);
    boolean modifyExpense(int id, Expense.expenseStatus status);
    Expense getExpenseById(int id);
    List<Expense> getAllExpenses();
    List<Expense> getAllEmployeeExpenseById(int id);
    List<Expense> getAllEmployeeExpenseByStatus(Expense.expenseStatus status);
}
