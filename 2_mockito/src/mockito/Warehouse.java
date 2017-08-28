package mockito;

import java.util.Set;

public interface Warehouse {
	public int getInventory(String productName);

	public void add(String productname, int quantity);

	public boolean hasInventory(String productname, int quantity);

	/**
	 * Throws RuntimeException when called and the inventory is insufficient.
	 */
	public void remove(String productname, int quantity);

	public Set<String> getProducts();
}
