import java.util.List;
import java.util.ArrayList;

public class Location {
	private Integer zipCode;
	private String town;
	private String streetAddress;
	private List<Shop> shops;
	private Supplier supplier;
	
	
	/**
	 * Constructor
	 * @param zipCode
	 * @param town
	 * @param streetAddress
	 */
	public Location(Integer zipCode, String town, String streetAddress) {
		this.zipCode = zipCode;
		this.town = town;
		this.streetAddress = streetAddress;		
		shops = new ArrayList<Shop>();
	}
	
	/**
	 * Adds a shop.
	 * @param shop
	 * @throws UocSuperMarketOnlineException
	 *  - SHOP_ALREADY_EXISTS: if the shop already exists
	 */
	public void addShop(Shop shop) throws UocSuperMarketOnlineException {
		
		//Check there is not still any shop in this location (no more than one shop per location is allowed)
		if (shops.size() > 0) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SHOP_ALREADY_EXISTS);
		}
		
		shops.add(shop);
		shop.setLocation(this);
	}
	
	/**
	 * Two locations are the same if they have the same town and zipCode.
	 * @param Object
	 */
	public boolean equals (Object o) {

		   if (o instanceof Location) {
		      Location target = (Location) o;

		      if (zipCode.equals(target.getZipCode()) && town.equals(target.getTown())) {
		         return true;
		      }
		   }

		   return false;
		}
	
	/**
	 * Get the shops
	 * @return the shops
	 */
	public List<Shop> getShops() {
		return shops;
	}

	/**
	 * get the zipCode
	 * @return the zipCode
	 */
	public Integer getZipCode() {
		return zipCode;
	}
	
	/**
	 * get the town
	 * @return the town
	 */
	public String getTown() {
		return town;
	}
	
	/**
	 * get the supplier
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * set the supplier
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	

}
