package dev.ledesma.tests.dao;

import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.dao.PostgresExpenseDAO;
import dev.ledesma.entities.Expense;
import dev.ledesma.utils.ExpenseCreator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ExpenseDAOTest {

    static Logger logger = Logger.getLogger(ExpenseDAOTest.class.getName());
    private static ExpenseDAO expDAO = new PostgresExpenseDAO();
    private static ExpenseCreator expenseCreator = new ExpenseCreator();
    private static int expenseIdList;

    @Test
    @Order(1)
    void createExpense() {

        boolean test = false;

        System.out.println("\nCreating Expenses...");
        for(Expense e: expenseCreator.expenses) {

            if(expDAO.createExpense(e) == null){
                test = false;
                System.out.println("Could Not Create Expense\n");
                break;
            }

            System.out.println(e);
            test = true;
        }

        expenseIdList = expenseCreator.expenses.size();
        System.out.println(expenseIdList);
        System.out.println("Created " + expenseCreator.expenses.size() + " Expenses\n");
        Assertions.assertTrue(test);
    }

    @Test
    @Order(2)
    void deleteExpense() {
        boolean test = false;
        Random r = new Random();
        int id = r.nextInt(101);

        if(!expDAO.deleteExpense(id)){
            test = false;
            System.out.println(expDAO.getExpenseById(id) + " Cannot be Deleted");
        }else{
            expDAO.deleteExpense(id);
            test = true;
        }

        Assertions.assertTrue(test);
    }

    @Test
    @Order(3)
    void updateExpense() {
        int c = 0;
        int size = 0;
        int id = 0;
        int categorySize = expenseCreator.category.length;
        int descriptionSize = expenseCreator.category.length;
        boolean test = false;

        Random r = new Random();
        Set<Integer> idSet = new HashSet<>();

        for (Expense e : expDAO.getAllExpenses()) {idSet.add(e.getId());}

        while(size > expDAO.getAllExpenses().size() || size == 0){
            size = r.nextInt(101);
        }
        System.out.println("Updating " + size + " Expenses...");

        for(int i = 0; i < size; i ++) {

            id = r.nextInt(101);
            System.out.println("Trying Id... " + id + " of " + 101);

            while (!idSet.contains(id) || expDAO.getExpenseById(id).getStatus() != Expense.expenseStatus.PENDING ) {
                id = r.nextInt(101);
                System.out.println("Trying Id... " + id + " of " + 101);
            }

            System.out.println("Original: " + expDAO.getExpenseById(id));

            Expense updated = new Expense();
            updated.setId(expDAO.getExpenseById(id).getId());
            updated.setAmount(r.nextInt(10000));
            long time = Instant.now().getEpochSecond() -
                    kotlin.random.Random.Default.nextInt(10000000);
            updated.setDate(time);
            updated.setCategory(expenseCreator.category[r.nextInt(categorySize)]);
            updated.setDescription(expenseCreator.category[r.nextInt(descriptionSize)]);
            updated.setStatus(Expense.expenseStatus.PENDING);
            updated.setEmployeeId(expDAO.getExpenseById(id).getEmployeeId());

            if (expDAO.updateExpense(updated) != null) {

                test = true;
                System.out.println("Updated: " + expDAO.getExpenseById(id));
                c++;

            } else {

                System.out.println("Failed to Update: " + expDAO.getExpenseById(id));
                test = false;

            }
        }
        System.out.println("Updated " + c + " Expenses\n");
        Assertions.assertTrue(test);
    }

    @Test
    @Order(4)
    void modifyExpense() {
        int id = 1;
        Expense.expenseStatus status = Expense.expenseStatus.APPROVED;
        Expense modifiedExpense = expDAO.modifyExpense(id, status);

        if(modifiedExpense != null){
            Assertions.assertTrue(true);
        }else {
            Assertions.assertTrue(false);
        }

    }

    @Test
    @Order(5)
    void getExpenseById() {
        boolean test = false;
        Random r = new Random();
        int id = r.nextInt(101);
        System.out.println(id);
        Expense expense = expDAO.getExpenseById(id);
        if(expense != null) {
            test = true;
            System.out.println(expense);
        }else{
            Assertions.assertTrue(test);
        }
    }

    @Test
    @Order(6)
    void getAllExpenses() {
        boolean test = false;
        int c =0;

        if(!expDAO.getAllExpenses().isEmpty()) {

            System.out.println("Retrieving All Expenses..");
            for (Expense e : expDAO.getAllExpenses()) {System.out.println(e); c++;}
            test = true;

        }else{test = false; System.out.println("Expenses is empty");}

        System.out.println("Retrieved " + c + " Expenses\n");
        Assertions.assertTrue(test);
    }

    @Test
    @Order(7)
    void getAllEmployeeExpenseById() {
        Set<Integer> employeeId = new HashSet<>();
        Random r = new Random();
        int id = r.nextInt(101);

        for(Expense e: expDAO.getAllExpenses()){
            employeeId.add(e.getEmployeeId());
        }

        while(!employeeId.contains(id)){ id = r.nextInt(101);}

        List<Expense> expenses = expDAO.getAllEmployeeExpenseById(id);
        System.out.println(expenses);
        Assertions.assertFalse(expenses.isEmpty());

    }

    @Test
    @Order(8)
    void getAllExpenseByStatus() {
        List<Expense> expenses = expDAO.getAllExpenseByStatus(Expense.expenseStatus.APPROVED);
        for(Expense e : expenses){
            System.out.println(e);
        }
        Assertions.assertFalse(expenses.isEmpty());
    }
}