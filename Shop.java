import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Shop {
	
	private Integer idShop;
	private Carrier carrier;
	private Location location;
	private List<Product> products;
	
	// ACLARIMENT !!
	//---------------------------------------------------------------------------------------------------
	// NO HE POGUT ENTENDRE PERQUE AL DOCUMENT UML NO EXISTEIX LA RELACIÓ ENTRE SHOP I SUPPLIER
	// I EN CANVI A LA DOCUMENTACIO HTML ES DEMANA LA IMPLEMENTACIÓ DELS MÈTODES addSupplier i getSuppliers
	// DAVANT EL DUBTE HE DEFINIT LA RELACIO 1 a 0..n entre Shop i Supplier
	// SI EL QUE ESTA BE ES EL UML ALESHORES NOMES CAL IGNORAR l'atribut suppliers i els mètodes
	// addSupplier i getSuppliers implementats
	
	private List<Supplier> suppliers;  
	 
	/**
	 * Constructor
	 * @param idShop
	 */
	public Shop(Integer idShop) {
		this.idShop = idShop;
		products = new ArrayList<Product>();
	}
	
	
	/**
	 * 
	 * Add a product in the shop
	 * @param product
	 * @throws UocSuperMarketOnlineException
	 * - SHOP_HAS_PRODUCT: if the shop already has the product
	 */
	public void	addProduct(Product product) throws UocSuperMarketOnlineException {
		
		if (products.contains(product)) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SHOP_HAS_PRODUCT);
		}
		products.add(product);		
	}
	
	/**
	 * Add a supplier in the shop
	 * @param supplier
	 * @throws UocSuperMarketOnlineException
	 * - SUPPLIER_ALREADY_EXISTS: if the supplier is already assigned to this shop
	 */	
	public void addSupplier(Supplier supplier) throws UocSuperMarketOnlineException {
		
		if (suppliers.contains(supplier)) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SUPPLIER_ALREADY_EXISTS);
		}
		suppliers.add(supplier);		
	}
	
	/**
	 * Remove the carrier from the shop. 
	 * @param id of the carrier to remove
	 * @throws UocSuperMarketOnlineException
	 * - CARRIER_DOESNT_EXIST: if the carrier doesn't exist
	 */
	public void removeCarrier(Integer idCarrier) throws UocSuperMarketOnlineException {		
		if (carrier.getIdCarrier() == idCarrier) {
			carrier = null;
		} else {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CARRIER_DOESNT_EXIST);		
		}
	}
	
	/**
	 * Remove a product from the shop. 
	 * @param id - id of the product to remove
	 * @return boolean - true if product removed or false if product id is not found
	 */
	public boolean removeProduct(Integer idProduct)  {

		for (Product product : getProducts()) {
			
			if (product.getIdProduct()== idProduct) {
				return products.remove(product);			
			}
		}
		
		return false;
	}
	
	
	// GETTERS & SETTERS
	
	/**
	 * get the shop id
	 * @return the idShop
	 */
	public Integer getIdShop() {
		return idShop;
	}

	/**
	 * get the carrier of the shop
	 * @return the carrier
	 */
	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * set the carrier of the shop
	 * @param carrier the carrier to set
	 */
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	/**
	 * get the location of the shop
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * set the location of the shop
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * get the products of the shop
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}
}
