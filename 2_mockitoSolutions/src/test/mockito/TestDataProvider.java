package test.mockito;

import main.mockito.Warehouse;
import main.mockito.WarehouseImpl;
import main.mockito.assignment1.Order;
import main.mockito.assignment2.OrderRevisitet;

import java.util.Set;

public class TestDataProvider {

    final static String TALISKER = "Talisker";
    final static String HIGHLAND_PARK = "Highland Park";
    final static int TALISKER_INVENTORY = 50;
    final static int HIGHLAND_PARK_INVENTORY = 25;

    public static Warehouse getDefaultTestWarehouse() {
        Warehouse defaultWarehouse = new WarehouseImpl();
        defaultWarehouse.add(TALISKER, TALISKER_INVENTORY);
        defaultWarehouse.add(HIGHLAND_PARK, HIGHLAND_PARK_INVENTORY);
        return defaultWarehouse;
    }

    public static Order getFillableOrder(Warehouse warehouse) {
        String productName = getOneProductName(warehouse);
        if(productName!=null){
            return new Order(productName, warehouse.getInventory(productName));
        }
        throw new RuntimeException("Unable to provide fillable order.");
    }

    private static String getOneProductName(Warehouse warehouse) {
        Set<String> products = warehouse.getProducts();
        if (products.size() > 0) {
            for (String product : products) {
                return product;
            }
        }
        return null;
    }

    public static Order getNonFillableOrder(Warehouse warehouse) {
        String productName = getOneProductName(warehouse);
        if(productName!=null){
            return new Order(productName, warehouse.getInventory(productName) + 1);
        }
        return new Order("dummyProductName", 1);
    }

    public static Order getDefaultOrder() {
        return new Order(TALISKER, TALISKER_INVENTORY);
    }

    public static OrderRevisitet getDefaultOrderRevisited() {
        return new OrderRevisitet(TALISKER, TALISKER_INVENTORY);
    }

    public static OrderRevisitet getFillableOrderRevisited(Warehouse warehouse) {
        String productName = getOneProductName(warehouse);
        if(productName!=null){
            return new OrderRevisitet(productName, warehouse.getInventory(productName));
        }
        throw new RuntimeException("Unable to provide fillable orderWithMailService.");
    }

    public static OrderRevisitet getNonFillableOrderRevisited(Warehouse warehouse) {
        String productName = getOneProductName(warehouse);
        if(productName!=null){
            return new OrderRevisitet(productName, warehouse.getInventory(productName) + 1);
        }
        return new OrderRevisitet("dummyProductName", 1);
    }
}
