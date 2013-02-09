
public class Senior extends Customer {

	private final Integer DISCOUNT = 5;
	private final double ADDITIONAL_PERCENTAGE_SENIOR = 0.05;
	
	private Integer rewardPoints;
	
	/**
	 * Constructor
	 * @param NIF, name, surname, email, phone, location
	 */
	
	
	public Senior(String NIF, String name, String surname, String email, String phone, Location location) {
		super(NIF, email, phone, name, surname, location);
	}

	/**
	 * @return the rewardPoints
	 */
	public Integer getRewardPoints() {
		return rewardPoints;
	}

	/**
	 * @param rewardPoints the rewardPoints to set
	 */
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	
	/**
	 * This method returns the discount for a senior customer
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
		price *= (1.0 + BASE_PERCENTAGE_INCREMENT + ADDITIONAL_PERCENTAGE_SENIOR);
		price *= (1.0 - discount().doubleValue()/100.0);		
		return price;
	}

}
