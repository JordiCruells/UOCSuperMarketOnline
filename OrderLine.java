
public class OrderLine {
	private Integer quantity;
	private Order order;
	private Product product;
	
	
	/**
	 * Constructor
	 * @param quantity - 
	 * @param order -  
	 * @param product -
	 */
	public OrderLine(Integer quantity, Order order, Product product) {
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}
	
	
	/**
	 * get the quantity
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * get the order
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * get the product
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * Get a summary of an order line
	 * @String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("OrderLine: quantity:" + getQuantity() + " idProduct:" + getProduct().getIdProduct() + " stock:" + getProduct().getStock());
		sb.append(UocSuperMarketOnline.NL);
		return sb.toString();
	}

}
