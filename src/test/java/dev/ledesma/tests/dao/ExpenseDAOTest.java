package dev.ledesma.tests.dao;

import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.dao.PostgresExpenseDAO;
import dev.ledesma.entities.Expense;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;


import java.util.HashSet;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpenseDAOTest {

    static Logger logger = Logger.getLogger(EmployeeDAOTest.class.getName());
    private static ExpenseDAO expDAO = new PostgresExpenseDAO();
    private static Expense testExpense = new Expense();


    @Test
    void createExpense() {
        Expense test = new Expense(2, 100, 3333, "bill", "azure database", Expense.expenseStatus.PENDING, 1);
        boolean expTest = expDAO.createExpense(test);
        System.out.print(test);
        Assertions.assertTrue(expTest);
    }

    @Test
    void deleteExpense() {
        Assertions.assertTrue(expDAO.deleteExpense(1));
    }

    @Test
    void updateExpense() {
        Expense test = new Expense(2, 100, 4444, "bill", "azure database", Expense.expenseStatus.PENDING, 1);
        boolean empTest = expDAO.updateExpense(test);
        Assertions.assertTrue(empTest);
    }

    @Test
    void modifyExpense() {
    }

    @Test
    void getExpenseById() {
        Expense test = new Expense();
        test = expDAO.getExpenseById(2);
        System.out.println(test);
        Assertions.assertInstanceOf(Expense.class, test);
    }

    @Test
    void getAllExpenses() {
        Set<Expense> expSet = new HashSet<>();
        expSet = expDAO.getAllExpenses();
        Expense test = new Expense(2, 100, 4444, "bill", "azure database", Expense.expenseStatus.PENDING, 1);
        System.out.print(expSet);
        Assertions.assertTrue(expSet.contains(test));
    }

    @Test
    void getAllEmployeeExpenseById() {
    }

    @Test
    void getAllExpenseByStatus() {
    }
}