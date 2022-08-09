package dev.ledesma.utils;

import dev.ledesma.dao.ExpenseDAO;
import dev.ledesma.dao.PostgresExpenseDAO;
import dev.ledesma.entities.Employee;
import dev.ledesma.entities.Expense;

import java.time.Instant;
import java.util.*;

public class ExpenseCreator {
    public static Random r = new Random();
    public List<Expense> expenses = new ArrayList<>();
    public expenseStatus status;
    public String[] category = {"Rent", "Transportation", "Car",
        "Travel", "Food", "Grocery", "Utility","Bill",
        "Cell Phone", "Childcare", "School",
        "Pet", "Clothing", "Personal", "Upkeep", "Health",
        "Subscription", "Insurance", "Entertainment",
        "Loan", "Credit Card", "Debt",
        "Retirement", "Emergency"};
    public enum expenseStatus{
        PENDING,
        APPROVED,
        DENIED;
        private static final List<Expense.expenseStatus> VALUES =
                Collections.unmodifiableList(Arrays.asList(Expense.expenseStatus.values()));
        private static final int SIZE = VALUES.size();

        public static Expense.expenseStatus randomStatus(){
            return VALUES.get(r.nextInt(SIZE));
        }
    }

    public ExpenseCreator(){
        Random r = new Random();
        for(int i = 0; i < 100; i++){
            Expense expense = new Expense();
            expense.setId(0);
            expense.setAmount(r.nextInt(10000));
            long time = Instant.now().getEpochSecond() - r.nextInt(10000000);
            expense.setDate(time);
            expense.setCategory(category[r.nextInt(category.length)]);
            expense.setDescription(category[r.nextInt(category.length)]);
            expense.setStatus(ExpenseCreator.expenseStatus.randomStatus());
            expense.setEmployeeId(r.nextInt(100) + 1);
            expenses.add(expense);
        }
    }

    public List<Expense> getExpenses(){return expenses;}
    public void createExpenseTable(){

        ExpenseDAO expenseDAO = new PostgresExpenseDAO();

            System.out.println("\nCreating Employees...");
            for(Expense e: expenses) {

                if(expenseDAO.createExpense(e) == null){
                    System.out.println(e);
                    System.out.println("Could Not Create Expense\n");
                }

                System.out.println(e);
            }
            System.out.println("Created " + expenses.size() + " Employees\n");
    }
}
