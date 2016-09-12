package mockito.assignment2;

import main.mockito.Warehouse;
import main.mockito.assignment1.Order;
import main.mockito.assignment2.OrderRevisitet;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class OrderRevisitetTest {

    @Test
    /**
     * Assignment 2a: Using a stub (not mock).
     * Implement MailServiceStub and use it here.
     * Assert that a mail is sent when the order is not filled.
     */
    public void mailSentIfOrderIsNotFilled() {
        fail("Not implemented");
    }

    @Test
    /**
     * Assignment 2b: Using a stub (not mock).
     * Implement MailServiceStub and use it here.
     * Assert that a mail is NOT sent when the order is filled.
     */
    public void mailNotSentWhenOrderIsFilled() {
        fail("Not implemented");
    }

    @Test
    /**
     * Assignment 2c: Using mocks.
     * Assert that a possible runtime exception thrown
     * when filling order is written to log.
     */
    public void runtimeExceptionOnFillIsWrittenToLog() {
        fail("Not implemented");
    }

    @Test
    /**
     * Assignment 2d: Using a spy.
     * Assert that a possible runtime exception thrown
     * when filling order is written to log.
     */
    public void runtimeExceptionOnFillIsWrittenToLogSpy() {
        fail("Not implemented");
    }

    @Test
    /**
     * Assignment 2e: CORRECT THE ERROR(S).
     * Mockist testing: When warehouse has enough inventory
     * for a specific order:
     * 		- verify that the order is filled
     */
    public void orderIsFilledWhenWarehouseCanProvide() {
        // ARRANGE
        Warehouse mockWarehouse = mock(Warehouse.class);
        when(mockWarehouse.hasInventory("TALISKER", anyInt()))
                .thenReturn(true);
        Order order = new Order("TALISKER", 20);
        // ACT
        order.fill(mockWarehouse);
        // ASSERT/VERIFY
        assertTrue(order.isFilled());
    }

    @Test
    /**
     * Assignment 2f: CORRECT THE ERROR(S).
     * Why do I see a panel when running this one?
     * Please correct it.
     */
    public void semanticsNotImportant() {
        OrderRevisitet order = new OrderRevisitet("TALISKER", 20);
        OrderRevisitet orderSpy = spy(order);
        when(orderSpy.returnStringMethod()).thenReturn("something");
        assertTrue(orderSpy.returnStringMethod().equals("something"));
    }
}