package mockito.assignment1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

    @Test
    /**
     * Assignment 1a
     * Classic testing: When warehouse has enough inventory
     * for a specific order:
     * 		- verify that the order is filled
     */
    public void orderIsFilledWhenWarehouseCanProvide() {
        fail("Not implemented");
    }

    @Test
    /**
     * Assignment 1b
     * Classic testing: When warehouse has enough inventory
     * for a specific order:
     * 		- verify that the inventory is updated accordingly
     */
    public void inventoryIsUpdatedWhenWarehouseFillsOrder() {
        fail("Not implemented");
    }

    @Test
    /**
     * Assignment 1c
     * Classic testing: When warehouse does not have enough inventory
     * for a specific order:
     * 		- verify that the order is not filled
     */
    public void orderIsNotFilledWhenWarehouseFailsToProvide() {
        fail("Not implemented");
    }

    @Test
    /**
     * Assignment 1d
     * Classic testing: When warehouse does not have enough inventory
     * for a specific order:
     * 		- verify that the warehouse inventory has not changed
     */
    public void inventoryIsUnchangedWhenOrderIsNotFilled() {
        fail("Not implemented");
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
        fail("Not implemented");
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
        fail("Not implemented");
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