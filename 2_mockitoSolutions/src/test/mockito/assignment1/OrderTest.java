package test.mockito.assignment1;

import main.mockito.Warehouse;
import main.mockito.assignment1.Order;
import org.junit.Test;
import test.mockito.TestDataProvider;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderTest {

    @Test
    /**
     * Assignment 1a
     * Classic testing: When warehouse has enough inventory
     * for a specific order:
     * 		- verify that the order is filled
     */
    public void orderIsFilledWhenWarehouseCanProvide() {
        // ARRANGE
        Warehouse normalWarehouse = TestDataProvider.getDefaultTestWarehouse();
        Order fillableOrder = TestDataProvider.getFillableOrder(normalWarehouse);
        assertFalse(fillableOrder.isFilled());
        // ACT
        fillableOrder.fill(normalWarehouse);
        // ASSERT
        assertTrue(fillableOrder.isFilled());
    }

    @Test
    /**
     * Assignment 1b
     * Classic testing: When warehouse has enough inventory
     * for a specific order:
     * 		- verify that the inventory is updated accordingly
     */
    public void inventoryIsUpdatedWhenWarehouseFillsOrder() {
        // ARRANGE
        Warehouse normalWarehouse = TestDataProvider.getDefaultTestWarehouse();
        Order fillableOrder = TestDataProvider.getFillableOrder(normalWarehouse);
        int initialInventory = normalWarehouse.getInventory(fillableOrder.getProductName());
        assertFalse(fillableOrder.isFilled());
        // ACT
        fillableOrder.fill(normalWarehouse);
        // ASSERT
        assertTrue(initialInventory == normalWarehouse.getInventory(fillableOrder.getProductName()) + fillableOrder.getQuantity());
    }

    @Test
    /**
     * Assignment 1c
     * Classic testing: When warehouse does not have enough inventory
     * for a specific order:
     * 		- verify that the order is not filled
     */
    public void orderIsNotFilledWhenWarehouseFailsToProvide() {
        // ARRANGE
        Warehouse normalWarehouse = TestDataProvider.getDefaultTestWarehouse();
        Order nonFillableOrder = TestDataProvider.getNonFillableOrder(normalWarehouse);
        // ACT
        nonFillableOrder.fill(normalWarehouse);
        // ASSERT
        assertFalse(nonFillableOrder.isFilled());
    }

    @Test
    /**
     * Assignment 1d
     * Classic testing: When warehouse does not have enough inventory
     * for a specific order:
     * 		- verify that the warehouse inventory has not changed
     */
    public void inventoryIsUnchangedWhenOrderIsNotFilled() {
        // ARRANGE
        Warehouse normalWarehouse = TestDataProvider.getDefaultTestWarehouse();
        Order nonFillableOrder = TestDataProvider.getNonFillableOrder(normalWarehouse);
        int initialInventory = normalWarehouse.getInventory(nonFillableOrder.getProductName());
        // ACT
        nonFillableOrder.fill(normalWarehouse);
        // ASSERT
        assertTrue(initialInventory == normalWarehouse.getInventory(nonFillableOrder.getProductName()));
    }

    @Test
    /**
     * Assignment 1e
     * Mock testing: When order is to be filled, mock a warehouse
     * providing enough inventory. Verify that both hasInventory()
     * and remove() is called in the warehouse.
     * Also make sure the order status is filled.
     */
    public void warehouseShouldCheckInventoryAndUpdateQuantityWhenNeeded() {
        // ARRANGE
        Warehouse mockWarehouse = mock(Warehouse.class);
        Order order = TestDataProvider.getDefaultOrder();
        // We decide what the mockWarehouse will return using when/thenReturn
        when(mockWarehouse.hasInventory(order.getProductName(), order.getQuantity())).thenReturn(true);
        // ACT
        order.fill(mockWarehouse);
        // ASSERT/VERIFY
        verify(mockWarehouse).hasInventory(order.getProductName(), order.getQuantity()); // Normally redundent,
        // see doc
        verify(mockWarehouse).remove(order.getProductName(), order.getQuantity());
        assertTrue(order.isFilled());
    }

    @Test
    /**
     * Assignment 1f
     * Mock testing: When order is to be filled and warehouse
     * cannot provide enough inventory, only hasInventory()
     * should be called in the warehouse.
     * Make sure remove() is NOT called.
     * Also make sure the order status is NOT filled.
     */
    public void warehouseShouldOnlyCheckInventoryWhenFillingIsImpossible() {
        // ARRANGE
        Warehouse mockWarehouse = mock(Warehouse.class);
        Order order = TestDataProvider.getDefaultOrder();
        // We decide what the mockWarehouse will return using when/thenReturn
        when(mockWarehouse.hasInventory(order.getProductName(), order.getQuantity())).thenReturn(false);
        // ACT
        order.fill(mockWarehouse);
        // ASSERT/VERIFY
        assertFalse(order.isFilled());
        verify(mockWarehouse).hasInventory(order.getProductName(), order.getQuantity());
        // Verify that remove was never called like this
        verify(mockWarehouse, never()).remove(anyString(), anyInt());
        // or this
        verifyNoMoreInteractions(mockWarehouse);
        assertFalse(order.isFilled());
    }

    @SuppressWarnings("rawtypes")
    @Test
    /**
     * Examples from lecture
     */
    public void examples() {
        List mockedList = mock(List.class);
        when(mockedList.get(0)).thenReturn("a");
        assertTrue(mockedList.get(0).equals("a"));
        assertNull(mockedList.get(1));
    }

}