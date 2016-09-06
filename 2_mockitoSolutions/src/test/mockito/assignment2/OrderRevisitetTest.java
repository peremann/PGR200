package test.mockito.assignment2;

import main.mockito.Warehouse;
import main.mockito.assignment1.Order;
import main.mockito.assignment2.OrderRevisitet;
import org.junit.Test;
import test.mockito.TestDataProvider;

import java.util.logging.Logger;

import static org.junit.Assert.*;
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
        // ARRANGE
        Warehouse normalWarehouse = TestDataProvider.getDefaultTestWarehouse();
        OrderRevisitet nonFillableOrder = TestDataProvider.getNonFillableOrderRevisited(normalWarehouse);
        MailServiceStub mailer = new MailServiceStub();
        nonFillableOrder.setMailService(mailer);
        // ACT
        nonFillableOrder.fill(normalWarehouse);
        // ASSERT
        assertEquals(1, mailer.numberSent());
    }

    @Test
    /**
     * Assignment 2b: Using a stub (not mock).
     * Implement MailServiceStub and use it here.
     * Assert that a mail is NOT sent when the order is filled.
     */
    public void mailNotSentWhenOrderIsFilled() {
        // ARRANGE
        Warehouse normalWarehouse = TestDataProvider.getDefaultTestWarehouse();
        OrderRevisitet fillableOrder = TestDataProvider.getFillableOrderRevisited(normalWarehouse);
        MailServiceStub mailer = new MailServiceStub();
        fillableOrder.setMailService(mailer);
        // ACT
        fillableOrder.fill(normalWarehouse);
        // ASSERT
        assertEquals(0, mailer.numberSent());
    }

    @Test
    /**
     * Assignment 2c: Using mocks.
     * Assert that a possible runtime exception thrown
     * when filling order is written to log.
     */
    public void runtimeExceptionOnFillIsWrittenToLog() {
        // ARRANGE
        Warehouse mockWareHouse = mock(Warehouse.class);
        when(mockWareHouse.hasInventory(anyString(), anyInt())).thenReturn(true);
        OrderRevisitet order = TestDataProvider.getDefaultOrderRevisited();
        Logger logger = addMockLogger(order);
        doThrow(new RuntimeException()).when(mockWareHouse).remove(anyString(), anyInt());
        // ACT
        order.fill(mockWareHouse);
        // ASSERT/VERIFY
        assertFalse(order.isFilled());
        verify(logger).info(anyString());
    }

    @Test
    /**
     * Assignment 2d: Using a spy.
     * Assert that a possible runtime exception thrown
     * when filling order is written to log.
     */
    public void runtimeExceptionOnFillIsWrittenToLogSpy() {
        // ARRANGE
        Warehouse normalWareHouse = TestDataProvider.getDefaultTestWarehouse();
        Warehouse warehouseSpy = spy(normalWareHouse);
        OrderRevisitet fillableOrder = TestDataProvider.getFillableOrderRevisited(warehouseSpy);
        Logger logger = addMockLogger(fillableOrder);
        // stubbing the remove-method in the spy
        doThrow(new RuntimeException()).when(warehouseSpy).remove(anyString(), anyInt());

        // ACT
        fillableOrder.fill(warehouseSpy);
        // ASSERT/VERIFY
        assertFalse(fillableOrder.isFilled());
        verify(logger).info(anyString());
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
        //when(mockWarehouse.hasInventory("TALISKER", anyInt()))
        //        .thenReturn(true);
        when(mockWarehouse.hasInventory(anyString(), anyInt())).thenReturn(true);
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
        //when(orderSpy.returnStringMethod()).thenReturn("something");
        doReturn("something").when(orderSpy).returnStringMethod();
        assertTrue(orderSpy.returnStringMethod().equals("something"));
    }

    private Logger addMockLogger(OrderRevisitet order) {
        Logger logger = mock(Logger.class);
        order.setLogger(logger);
        return logger;
    }
}