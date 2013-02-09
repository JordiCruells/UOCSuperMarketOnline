import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Order {
	private Date date;
	private Integer orderNbr;
	private Boolean state;
	private Customer customer;
	private List<OrderLine> orderlines;
		
	/**
	 * Constructor
	 * @param orderNbr
	 * @param date
	 */
	public Order(Integer orderNbr, Date date){
		this.date = date;
		this.orderNbr = orderNbr;
		this.state = true;
		orderlines = new ArrayList<OrderLine>();
	}
	
	/**
	 * Adds an orderline to this order.
	 * @param orderline
	 */
	public void addOrderline(OrderLine orderline) {
		
	   /* AQUEST CODI ESTA COMENTAT PERQUE CREC QUE L'EXERCICI NO DEMANA COMPROBAR QUE EN UNA ORDRE
	    * NO HI PUGUI HAVER LINIES REPETIDES DEL MATEIX PRODUCTE (DESCOMENTANT AQUEST CODI I DEFININT L'ERROR
	    * PRODUCT_ALREADY_EXISTS QUEDARIA IMPLEMENTAT
	    * Product p = orderline.getProduct();
		* if (this.findProductInOrder(p)) {
		* 	throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.PRODUCT_ALREADY_EXISTS);
		* }
		*/
		
		orderlines.add(orderline);
			
	}
	
	/**
	 * Close an order
	 * @ throws UocSuperMarketOnlineException
	 * - ORDER_CLOSED: if the order is already closed 
	 */
	public void closeOrder()  throws UocSuperMarketOnlineException {
		if (state == false) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_CLOSED);			
		}
		state = false;
	}
	
	/**
	 * Two orders are equal if have the same order number
	 * @param Object
	 */
	public boolean equals(Object o){
		
		if (o instanceof Order) {
			if (orderNbr == ((Order)o).getOrderNbr()) {
				return true;
			}	
		}
		return false;
	}

	/**
	 * Prints the order.
	 * @return String
	 */
	public String toString()  {
		StringBuilder sb = new StringBuilder();
		sb.append("Order: orderNbr:" + this.getOrderNbr() + " state:" + this.getState());
		sb.append(UocSuperMarketOnline.NL);
		return sb.toString();
	}
	
	
	/**
	 * get the customer
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}


	/**
	 * set the customer
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * get the state
	 * @return the state
	 */
	public Boolean getState() {
		return state;
	}


	/**
	 * get the order number
	 * @return the orderNbr
	 */
	public Integer getOrderNbr() {
		return orderNbr;
	}


	/**
	 * get the order lines
	 * @return the orderlines
	 */
	public List<OrderLine> getOrderlines() {
		return orderlines;
	}
	
	
}
