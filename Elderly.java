import java.util.Date;
public class Elderly extends Customer {

	private final Integer DISCOUNT = 8;
	private final double ADDITIONAL_PERCENTAGE_ELDERLY = 0.02;
		
	private Date dateOfBirth;
	
	/**
	 * Constructor
	 * @param NIF, email, phone, name, surname, dateOfBirth
	 */
	
		
	public Elderly(String NIF, String name, String surname, String email, String phone, Date dateOfBirth, Location location) {
		super(NIF, email, phone, name, surname, location);
		this.dateOfBirth = dateOfBirth;
	}

	
	/**
	 * This method returns the discount for an elderly customer
	 * @return float
	 */
	public Integer discount(){
		return this.DISCOUNT;
	}
	
	
	// PRIVATE & PROTECTED METHODS
	/**
	 * This method returns the price of the order considering all charges and discounts
	 * @return Double
	 * @throws UocSuperMarketOnlineException
	 * - ORDER_DOESNT_EXIST: if the order is not found 
	 */
	protected Double calculatePrice(Integer order) throws UocSuperMarketOnlineException {
		
		double price = 0.0;				
		price = baseCost(order);		
		price *= (1.0 + BASE_PERCENTAGE_INCREMENT + ADDITIONAL_PERCENTAGE_ELDERLY);
		price *= (1.0 - discount().doubleValue()/100.0);		
		
		return price;
	}
}