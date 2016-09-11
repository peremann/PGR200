package no.westerdals;

import java.util.HashMap;
import java.util.Set;

public class WarehouseImpl implements Warehouse{

	private HashMap<String, Integer> inventory = new HashMap<String, Integer>();
	
	public int getInventory(String productName) {
		if(inventory.containsKey(productName)){
			return inventory.get(productName).intValue();
		}
		return 0;
	}

	public void add(String productname, int quantity) {
		int currentQuantity = getInventory(productname);
		inventory.put(productname, currentQuantity + quantity);
	}

	public boolean hasInventory(String productname, int quantity) {
		return getInventory(productname)>=quantity;
	}

	public void remove(String productname, int quantity) {
		if(!hasInventory(productname, quantity)){
			throw new InventoryException("Not enough inventory: " + productname + ", "+quantity);
		}
		int currentQuantity = getInventory(productname);
		inventory.put(productname, currentQuantity - quantity);
	}

	public Set<String> getProducts() {
		return inventory.keySet();
	}

	
}
