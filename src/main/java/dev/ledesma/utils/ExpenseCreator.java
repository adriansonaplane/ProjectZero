package dev.ledesma.utils;

import dev.ledesma.entities.Expense;
import kotlin.random.Random;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//import java.util.*;

public class ExpenseCreator {

//    public Random r = new Random();
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
            return VALUES.get(Random.Default.nextInt(SIZE));
        }
    }

    public ExpenseCreator(){
        for(int i = 0; i < 500; i++){
            Expense expense = new Expense();
            expense.setAmount(Random.Default.nextInt(10000));
            long time = Instant.now().getEpochSecond() - Random.Default.nextInt(10000000);
            expense.setDate(time);
            expense.setCategory(category[Random.Default.nextInt(category.length)]);
            expense.setDescription(category[Random.Default.nextInt(category.length)]);
            expense.setStatus(ExpenseCreator.expenseStatus.randomStatus());
            expense.setEmployeeId(Random.Default.nextInt(100));//Employee Creator Constructor Condition
            expenses.add(expense);
        }
    }

    public List<Expense> getExpenses(){return expenses;}
}
