package dev.ledesma.tests.dao;

import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.dao.PostgresExpenseDAO;
import dev.ledesma.entities.Expense;
import dev.ledesma.utils.ExpenseCreator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpenseDAOTest {

    static Logger logger = Logger.getLogger(EmployeeDAOTest.class.getName());
    private static ExpenseDAO expDAO = new PostgresExpenseDAO();
    private static ExpenseCreator expenses = new ExpenseCreator();

    @Test
    void createExpense() {
        Expense test = new Expense(2, 100, 3333, "bill", "azure database", Expense.expenseStatus.PENDING, 1);
        System.out.print(test);
        Assertions.assertTrue(expDAO.createExpense(test));
    }

    @Test
    void deleteExpense() {
        Assertions.assertTrue(expDAO.deleteExpense(5));
    }

    @Test
    void updateExpense() {
        Expense test = new Expense(2, 100, 4444, "bill", "azure database", Expense.expenseStatus.PENDING, 1);
        System.out.println(test);
        Assertions.assertTrue(expDAO.updateExpense(test));
    }

    @Test
    void modifyExpense() {
    }

    @Test
    void getExpenseById() {
        Expense test = expDAO.getExpenseById(2);
        System.out.println(test);
        Assertions.assertInstanceOf(Expense.class, test);
    }

    @Test
    void getAllExpenses() {

        List<Expense> expenses = expDAO.getAllExpenses();
        System.out.println(expenses);
        Assertions.assertFalse(expenses.isEmpty());
    }

    @Test
    void getAllEmployeeExpenseById() {
        List<Expense> expenses = expDAO.getAllEmployeeExpenseById(2);
        System.out.println(expenses);
        Assertions.assertFalse(expenses.isEmpty());

    }

    @Test
    void getAllExpenseByStatus() {
        List<Expense> expenses = expDAO.getAllExpenseByStatus(Expense.expenseStatus.APPROVED);
        System.out.println(expenses);
        Assertions.assertFalse(expenses.isEmpty());
    }

    @Test
    void getGeneratedExpenses(){
        List<Expense> expenseList = expenses.getExpenses();
        for(Expense e : expenseList){
            System.out.print(e.toString() + "\n");// + Instant.ofEpochSecond(e.getDate()) + "\n"
        }
    }
}