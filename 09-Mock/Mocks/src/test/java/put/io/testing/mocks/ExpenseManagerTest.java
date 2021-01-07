package put.io.testing.mocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;


import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;

public class ExpenseManagerTest {

    @Test
    public void calctotal(){
        IFancyDatabase mockObject= mock(IFancyDatabase.class);
        ExpenseRepository mockRepo =mock(ExpenseRepository.class);
        Expense exp1=new Expense();
        Expense exp2=new Expense();
        Expense exp3=new Expense();
        exp1.setAmount(10);
        exp2.setAmount(20);
        exp3.setAmount(30);
        ArrayList<Expense> expenses = new ArrayList<>();

        expenses.add(exp1);
        expenses.add(exp2);
        expenses.add(exp3);
        when(mockRepo.getExpenses()).thenReturn(expenses);

        ExpenseManager manager= new ExpenseManager(mockObject,mockRepo);

        assertTrue(manager.calculateTotal()==10+20+30);


    }




    @Test
    public void calctotal4Category(){
        IFancyDatabase mockObject= mock(IFancyDatabase.class);
        ExpenseRepository mockRepo =mock(ExpenseRepository.class);


        Expense exp1=new Expense();
        Expense exp2=new Expense();
        Expense exp3=new Expense();
        exp1.setAmount(10);
        exp2.setAmount(20);
        exp3.setAmount(30);
        ArrayList<Expense> expenses = new ArrayList<>();
        ArrayList<Expense> expensesEmpty = new ArrayList<>();
        when(mockRepo.getExpensesByCategory(any())).thenReturn(expensesEmpty);

        expenses.add(exp1);
        expenses.add(exp2);
        expenses.add(exp3);

        when(mockRepo.getExpensesByCategory("Home")).thenReturn(expenses);
        when(mockRepo.getExpensesByCategory("Car")).thenReturn(expenses);

        ExpenseManager manager = new ExpenseManager(mockObject,mockRepo);
        assertTrue(manager.calculateTotalForCategory("Home")==60);
        assertTrue(manager.calculateTotalForCategory("Car")==60);

        assertTrue(manager.calculateTotalForCategory("Food")==0);
        assertTrue(manager.calculateTotalForCategory("Sport")==0);


    }


    @Test
    public void waluta() throws ConnectException {
        IFancyDatabase mockObject= mock(IFancyDatabase.class);
        ExpenseRepository mockRepo = mock(ExpenseRepository.class);
        ExpenseRepository expenseRepository=new ExpenseRepository(mockObject);

        Expense exp1=new Expense();
        Expense exp2=new Expense();
        Expense exp3=new Expense();
        exp1.setAmount(10);
        exp2.setAmount(20);
        exp3.setAmount(30);
        expenseRepository.addExpense(exp1);
        expenseRepository.addExpense(exp2);
        expenseRepository.addExpense(exp3);

        FancyService mockFancy= mock(FancyService.class);
        //when(mockFancy.convert(anyDouble(),eq("PLN"),eq("USD"))).thenReturn(new FancyService().convert(anyDouble(),eq("PLN"),eq("USD")));
       // when(mockFancy.convert(anyDouble(),eq("PLN"),eq("USD"))).thenThrow(new ConnectException());
        when(mockFancy.convert(anyDouble(),eq("PLN"),eq("USD"))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                 return (Double)args[0]/4;
            }
        });


      //  ExpenseManager manager = new ExpenseManager(mockObject,expenseRepository);
       // assertEquals( manager.calculateTotalInDollars(),15);

        assertEquals(mockFancy.convert(60,"PLN","USD"),15);


    }
}
