package dev.ledesma.tests.utility;

import dev.ledesma.entities.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.ledesma.utils.ExpenseCreator;
import java.util.List;


class ExpenseCreatorTest {

    @Test
    void getExpenses(){
        boolean test = false;
        ExpenseCreator expenseCreator = new ExpenseCreator();

        if(!expenseCreator.expenses.isEmpty()){
            List<Expense> expenseList = expenseCreator.getExpenses();
            for(Expense e : expenseList){
                System.out.println(e.toString() + "\n");
            }
            test = true;
        }
        Assertions.assertTrue(test);
    }

}