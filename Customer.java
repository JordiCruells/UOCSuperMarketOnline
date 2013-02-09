import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public abstract class Customer {

	private boolean blocked;
	private String NIF;
	private String email;
	private String phone;
	private String name;
	private String surname;	
	private Location location;
	private List<Order> orders;
	
	
	private static final String NL = System.getProperty("line.separator");
	public static final Double BASE_PERCENTAGE_INCREMENT = 0.10;
	public static final Double COST_DELIVERY = 10.0;
	
	/**
	 * Constructor
	 * @param  NIF
	 * @param name
	 * @param surname
	 * @param email
	 * @param phone
	 * @param location
	 */
	public Customer(String NIF, String name, String surname, String email, String phone, Location location){
		this.blocked = false;
		this.NIF = NIF;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.surname = surname;	
		this.orders = new ArrayList<Order>();
		this.location = location;
	}

	/**
	 * Add an order to the customer
	 * @param order
	 * @throws UocSuperMarketOnlineException
	 * - ORDER_DOESNT_EXIST: if the order is not found 
	 */
	public void addOrder(Order order) throws UocSuperMarketOnlineException {
		orders.add(order);
	}
	
	/**
	 * Remove all the orders of a customer
	 */
	public void removeAllOrders() {
		
		for (int i = 0; i < orders.size(); i++) {
			// remove the order
			orders.remove(i);
			
		}
	}
	
	/**
	 * Fetch an order given its id number
	 * @param the order to remove
	 */
	public Order fetchOrder(Integer orderNbr) {
		Iterator<Order> i = orders.iterator();
		Order order = null;
		while(i.hasNext()) {
			order = i.next(); 
			if (order.getOrderNbr().equals(orderNbr)) {
				return order;			
			}
		}		
		return null;
	}
	
	/**
	 * Remove an order of a customer
	 * @param the order to remove
	 * @throws UocSuperMarketOnlineException if the order to remove is not found
	 * - ORDER_DOESNT_EXIST: if the order is not found 
	 */
	public void removeOrder(Order order) throws UocSuperMarketOnlineException  {
		
		if (!(orders.remove(order))) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_DOESNT_EXIST);
		}
			
	}
	
	/**
	 * Returns a string that lists all the active orders for a customer
	 * @return String 
	 */
	public String  printActiveOrders() {
		StringBuilder sb = new StringBuilder();		
		for (Order order: getOrders()) {
			if (order.getState() == true) {
				sb.append("Order: " + order.getOrderNbr() + NL);
			}
		}		
		return sb.toString();
	}
	
	/**
	 * Returns a string listing all the orders of a customer
	 * @return String 
	 */
	public String  printOrders() {
		StringBuilder sb = new StringBuilder("");
		
		for (Order order: getOrders()) {
			sb.append(order);
			for (OrderLine orderline: order.getOrderlines()) {
				sb.append(orderline);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Check the order applying discounts and costs.
	 * @param order
	 * @return a String with the total cost
	 * - ORDER_DOESNT_EXIST: if the order is not found 
	 */
	public String salesCheck(Integer order) throws UocSuperMarketOnlineException {
		
		StringBuilder sb = new StringBuilder();
				
		Order o = findOrder(order);
		if (o == null) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_DOESNT_EXIST);
		}
		
		sb.append("Customer: " + getNIF() + UocSuperMarketOnline.NL);
		sb.append(o);
		for (OrderLine ol: o.getOrderlines()) {
			sb.append(ol);			
		}
		
		NumberFormat formatter = new DecimalFormat("#.#");
		
		String price = formatter.format(calculatePrice(order));
		
		sb.append("Total cost: " + price);
		
		
		return sb.toString();
	}
	
	/**
	 * Abstract method that must be overwritten in subtypes of Customer
	 * @return float value of the discount for a Customer
	 */
	public abstract Integer discount();
	

	// GETTERS & SETTERS
	
	/**
	 * Get if the customer is blocked
	 * @return the blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * set blocked value  
	 * @param blocked the blocked to set
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	/**
	 * get the NIF
	 * @return the nIF
	 */
	public String getNIF() {
		return NIF;
	}


	/**
	 * get the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the surname
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * get the location
	 * @return the surname
	 */
	public Location getLocation() {
		return location;
	}

	
	
	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}
	
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	//PRIVATE & PROTECTED METHODS
	/**
	 * Computes the base cost (without charges and discounts)
	 * @param order - the number of the order
	 * @return Double - the computed amount
	 * @throws UocSuperMarketOnlineException
	 * - ORDER_DOESNT_EXIST: if the order is not found 
	 */
	protected Double baseCost(Integer order) throws UocSuperMarketOnlineException {
		
		double cost = 0.0;
				
		Order o = findOrder(order);
		if (o == null) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_DOESNT_EXIST);
		}
		
		// Get the order lines to compute the price
		List<OrderLine> orderlines = o.getOrderlines();
		OrderLine ol;
		Product p;
		
		Iterator<OrderLine> i = orderlines.iterator();
		
		while(i.hasNext()) {
			ol = i.next();
			p = ol.getProduct();
			cost += p.getCostPrice() * ol.getQuantity();
		}
		
		return cost;
		
	}
	
	/**
	 * This method must be implemented inn the subclasses to return the price of the order
	 * @param order - the number of the order
	 * @return Double - the computed amount
	 * @throws UocSuperMarketOnlineException
	 * - ORDER_DOESNT_EXIST: if the order is not found 
	 */
	protected abstract Double calculatePrice(Integer order) throws UocSuperMarketOnlineException;
	
	/*
	 * Return an order of this customer given a its order number 
	 */
	private Order findOrder(Integer orderNbr) {
		
		Iterator<Order> i = orders.iterator();
		Order o;
		while(i.hasNext()) {
			o = i.next();
			if(o.getOrderNbr() == orderNbr) {
				return o;
			}
		}
		return null;		
	}
	
	
}
