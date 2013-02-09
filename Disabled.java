public class Disabled extends Customer {

	private final Integer DISCOUNT = 8;
	private final double ADDITIONAL_PERCENTAGE_DISABLED = 0.02;
		
	private Integer disabilityCardNbr;
	
	/**
	 * Constructor
	 * @param NIF, email, phone, name, surname, disabilityCardNbr
	 */
	
	
	public Disabled(String NIF, String name, String surname, String email, String phone, Integer disabilityCardNbr, Location location) {
		super(NIF, email, phone, name, surname, location);
		this.disabilityCardNbr = disabilityCardNbr;
	}

	
	
	/**
	 * This method returns the discount for a disabled customer
	 * @return Integer
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
		price *= (1.0 + BASE_PERCENTAGE_INCREMENT + ADDITIONAL_PERCENTAGE_DISABLED);
		price *= (1.0 - discount().doubleValue()/100.0);		
		
		return price;
	}
	
}