import java.util.List;
import java.util.ArrayList;
public class Product {
	
	private Integer idProduct;
	private String name;
	private String description;
	private Boolean fresh;
	private String label;
	private Integer stock;
	private Double costPrice;
	private Supplier supplier;
	private List<OrderLine> orderlines;
	
	/**
	 * Constructor
	 * @param fresh
	 * @param label
	 * @param idProduct
	 * @param name
	 * @param description
	 * @param stock
	 * @param costPrice
	 * @param supplier
	 */
	public Product(Boolean fresh, String label, Integer idProduct, String name, String description, Integer stock, Double costPrice, Supplier supplier) {
		
		this.idProduct = idProduct;
		this.name = name;
		this.description = description;
		this.fresh = fresh;
		this.label = label;
		this.stock = stock;
		this.costPrice = costPrice;
		this.supplier = supplier;
		orderlines = new ArrayList<OrderLine>();
		
	}
	
	/**
	 * Adds an orderline.
	 * @param orderline
	 */
	public void addOrderline(OrderLine orderline) {
		
		orderlines.add(orderline);
		
	}
	/**
	 * Adds order with a quantity.
	 * @param o
	 * @param q
	 * @throws UocSuperMarketOnlineException
	 * - NOT_ENOUGH_STOCK: if the stock of the product is not enough for the order  
	 */
	public void addToOrder(Order o, Integer q) throws UocSuperMarketOnlineException {
		
		//Check there is enough stock
		if (q > this.getStock()) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NOT_ENOUGHT_STOCK);
		}
		
		OrderLine ol = new OrderLine(q, o, this);
		o.addOrderline(ol);
		this.addOrderline(ol);
		
		// Update stock
		Integer newStock = this.getStock() - q;
		this.setStock(newStock);
	}
	
	/**
	 * Get a summary of a product.
	 * @return String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Product:" + this.getName() + "Description:" + this.getDescription());		
		return sb.toString();		
	}
	
	/**
	 * Two products are the same if they have the same idProduct
	 * @param Object
	 */
	public boolean equals(Object o) {
		if (o instanceof Product) {
			if (this.getIdProduct() == ((Product)o).getIdProduct()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	// GETTERS & SETTERS
	
	
	/**
	 * get the stock for the product
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set the stock for the product
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * get the cost price for this product
	 * @return the costPrice
	 */
	public Double getCostPrice() {
		return costPrice;
	}


	/**
	 * fet the product id
	 * @return the idProduct
	 */
	public Integer getIdProduct() {
		return idProduct;
	}

	
	

	/**
	 * get the order lines 
	 * @return the orderlines
	 */
	public List<OrderLine> getOrderlines() {
		return orderlines;
	}
}
