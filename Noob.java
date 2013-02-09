import java.util.Date;
public class Noob extends Customer {

	private final Integer DISCOUNT = 0;
	private final double ADDITIONAL_PERCENTAGE_NOOB = 0.1;
	private Date startDate;
	
	/**
	 * Constructor
	 * @param NIF, name, surname, email, phone, startDate, location
	 */
	
	public Noob(String NIF, String name, String surname, String email, String phone, Date startDate, Location location) {
		super(NIF, email, phone, name, surname, location);
		this.startDate = startDate;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * This method returns the discount for a noob customer
	 * @return double
	 */
	public Integer discount(){
		return this.DISCOUNT;
	}
	
	// PRIVATE & PROTECTED METHODS
	/**
	 * This method returns the price of the order considering all charges and discounts
	 * @return Double
	 * @throws UocSuperMarketOnlineException
	 *  - ORDER_DOESNT_EXIST: if the order is not found 
	 */
	protected Double calculatePrice(Integer order) throws UocSuperMarketOnlineException {
		
		double price = 0.0;				
		price = baseCost(order);		
		price *= (1.0 + BASE_PERCENTAGE_INCREMENT + ADDITIONAL_PERCENTAGE_NOOB);
		price *= (1.0 - discount().doubleValue()/100.0);		
		price += (price > 150.0) ? 0.0 : COST_DELIVERY;
		
		return price;
	}
	
}