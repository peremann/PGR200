package mockito.assignment1;

import mockito.Warehouse;


public class Order {

	private String productName;
	private int quantity;
	boolean filled;

	public Order(String productName, int quantity) {
		this.productName = productName;
		this.quantity = quantity;
		this.filled = false;
	}

	public void fill(Warehouse warehouse) {
		if (warehouse.hasInventory(productName, quantity)) {
			warehouse.remove(productName, quantity);
			filled = true;
		}
	}

	public boolean isFilled() {
		return filled;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
