package dev.ledesma.dao;

import dev.ledesma.entities.Expense;

import java.util.List;

public interface ExpenseDAO {


    Expense createExpense(Expense expense);
    Expense createExpense(int id, Expense expense);
    boolean deleteExpense(int id);
    Expense updateExpense(Expense expense);
    Expense modifyExpense(int id, Expense.expenseStatus status);
    Expense getExpenseById(int id);
    List<Expense> getAllExpenses();
    List<Expense> getAllEmployeeExpenseById(int id);
    List<Expense> getAllExpenseByStatus(Expense.expenseStatus status);



}
