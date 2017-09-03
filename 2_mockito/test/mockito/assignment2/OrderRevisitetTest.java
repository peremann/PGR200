package mockito.assignment2;

import mockito.Warehouse;
import mockito.WarehouseImpl;
import mockito.assignment1.Order;
import mockito.assignment2.OrderRevisitet;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
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
        OrderRevisitet order = new OrderRevisitet("potato", 1);
        Warehouse wh = new WarehouseImpl();
        MailServiceStub mss = new MailServiceStub();
        order.setMailService(mss);

        order.fill(wh);

        assertTrue(mss.getSent() == 1);
    }

    @Test
    /**
     * Assignment 2b: Using a stub (not mock).
     * Implement MailServiceStub and use it here.
     * Assert that a mail is NOT sent when the order is filled.
     */
    public void mailNotSentWhenOrderIsFilled() {
        OrderRevisitet order = new OrderRevisitet("potato", 1);
        Warehouse wh = new WarehouseImpl();
        MailServiceStub mss = new MailServiceStub();
        order.setMailService(mss);
        wh.add("potato", 1);

        order.fill(wh);

        assertTrue(mss.getSent() == 0);
    }

    @Test
    /**
     * Assignment 2c: Using mocks.
     * Assert that a possible runtime exception thrown
     * when filling order is written to log.
     */
    public void runtimeExceptionOnFillIsWrittenToLog() {
        OrderRevisitet order = new OrderRevisitet("potato", 1);
        Warehouse wh = mock(WarehouseImpl.class);
        Logger log = mock(Logger.class);
        order.setLogger(log);
        when(wh.hasInventory(anyString(), anyInt())).thenReturn(true);
        doThrow(new RuntimeException()).when(wh).remove(anyString(), anyInt());

        order.fill(wh);

        assertFalse(order.isFilled());
        verify(log).info(anyString());
    }

    @Test
    /**
     * Assignment 2d: Using a spy.
     * Assert that a possible runtime exception thrown
     * when filling order is written to log.
     */
    public void runtimeExceptionOnFillIsWrittenToLogSpy() {
        OrderRevisitet order = new OrderRevisitet("potato", 1);
        Warehouse wh = spy(new WarehouseImpl());
        when(wh.hasInventory(anyString(), anyInt())).thenReturn(true);
        doThrow(new RuntimeException()).when(wh).remove(anyString(), anyInt());
        Logger log = mock(Logger.class);
        order.setLogger(log);

        order.fill(wh);

        assertFalse(order.isFilled());
        verify(log).info(anyString());
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
        when(mockWarehouse.hasInventory(anyString(), anyInt()))
                .thenReturn(true);
        Order order = new Order(anyString(), anyInt());
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