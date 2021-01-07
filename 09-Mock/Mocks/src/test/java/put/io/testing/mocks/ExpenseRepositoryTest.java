package put.io.testing.mocks;
import static org.mockito.Mockito.*;
import put.io.testing.mocks.ExpenseManager;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseRepositoryTest {
    private MyDatabase mydb=new MyDatabase();
    private ExpenseRepository expenseRepository=new ExpenseRepository(mydb);

    @Test
    public void testEmpty(){
        IFancyDatabase mockObject= mock(IFancyDatabase.class);
        when(mockObject.queryAll()).thenReturn(Collections.emptyList());
        expenseRepository=new ExpenseRepository(mockObject);
        expenseRepository.loadExpenses();

        //verify(mockObject).close();
        InOrder inOrder = inOrder(mockObject);
        inOrder.verify(mockObject).connect();
        inOrder.verify(mockObject).queryAll();
        inOrder.verify(mockObject).close();

        assertTrue(expenseRepository.getExpenses().isEmpty());
    }


    @Test
    public void testAdd(){
        IFancyDatabase mockObject= mock(IFancyDatabase.class);
        when(mockObject.queryAll()).thenReturn(Collections.emptyList());
        expenseRepository=new ExpenseRepository(mockObject);
        Expense expense=new Expense();
        Expense sec =new Expense();
        Expense third=new Expense();
        Expense fourth =new Expense();
        Expense fifth = new Expense();
        expenseRepository.addExpense(expense);
        expenseRepository.addExpense(sec);
        expenseRepository.addExpense(third);
        expenseRepository.addExpense(fourth);
        expenseRepository.addExpense(fifth);
        expenseRepository.saveExpenses();
        expenseRepository.loadExpenses();

        verify(mockObject, times(5)).persist(any());
        //verify(mockObject,atLeastOnce()).persist(any());

        //verify(mockObject).close();
     /*   InOrder inOrder = inOrder(mockObject);
        inOrder.verify(mockObject).connect();
        inOrder.verify(mockObject).queryAll();
        inOrder.verify(mockObject).close();
*/
        assertTrue(expenseRepository.getExpenses().isEmpty());
    }






}
