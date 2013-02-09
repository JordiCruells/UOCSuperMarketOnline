
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Represents a uoc Super Market OnLine
 * @author OOP teaching staff
 * @version 
 * @since 
 */
public class UocSuperMarketOnline {

    // List of customers
    private List<Customer> customers;
    //List of suppliers
    private List<Supplier> suppliers;
    //List of shops
    private List<Shop> shops;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
    public static final String NL = System.getProperty("line.separator");
    
    

    /**
     * Default Constructor.
     */
    public UocSuperMarketOnline() {
        customers = new ArrayList<Customer>();
        suppliers = new ArrayList<Supplier>();
        shops = new ArrayList<Shop>();


    }

    /**
     * Use case 1 - Create and adds a noob to the supermarket.
     * 	
     * 
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null    
     * @param NIF
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param dateOfBirth
     * @param zipCode
     * @param town
     * @param streetAddress
     * @throws UocSuperMarketOnlineException 
     */
    public void createNoob(String NIF, String name, String surname, String email, String phone, String startDate, String zipCode, String town, String streetAddress) throws UocSuperMarketOnlineException {
        if ((NIF.isEmpty()) || (name.isEmpty()) || (surname.isEmpty()) || (email.isEmpty()) || (phone.isEmpty()) || (startDate.isEmpty()) || (zipCode.isEmpty()) || (town.isEmpty()) || (streetAddress.isEmpty())) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Date d = null;
            try {
                d = sdf.parse(startDate);
            } catch (ParseException e) {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.WRONG_DATE);
            }

            if (findCustomer(NIF) == null) {
                Integer zip = Integer.valueOf(zipCode);
                Location l = new Location(zip, town, streetAddress);
                customers.add(new Noob(NIF, name, surname, email, phone, d, l));

            } else {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_ALREADY_EXISTS);
            }
        }
    }

    /**
     * Use case 2 - Creates and Adds a Senior to the supermarket.
     * 	
     * 
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * CUSTOMER_ALREADY_EXISTS if the customer exists
    
     * @param NIF
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param zipCode
     * @param town
     * @param streetAddress
     * @throws UocSuperMarketOnlineException 
     */
    public void createSenior(String NIF, String name, String surname, String email, String phone, String zipCode, String town, String streetAddress) throws UocSuperMarketOnlineException {
        if ((NIF.isEmpty()) || (name.isEmpty()) || (surname.isEmpty()) || (email.isEmpty()) || (phone.isEmpty()) || (zipCode.isEmpty()) || (town.isEmpty()) || (streetAddress.isEmpty())) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {


            if (findCustomer(NIF) == null) {
                Integer zip = Integer.valueOf(zipCode);
                Location l = new Location(zip, town, streetAddress);
                customers.add(new Senior(NIF, name, surname, email, phone, l));

            } else {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_ALREADY_EXISTS);
            }


        }
    }

    /**
     * Use case 3 - Adds a Disabled to the supermarket.
     *
     *
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * CUSTOMER_ALREADY_EXISTS if the customer exists
    
     * @param NIF
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param disabilityCardNbr
     * @param zipCode
     * @param town
     * @param streetAddress
     * @throws UocSuperMarketOnlineException 
     */
    public void createDisabled(String NIF, String name, String surname, String email, String phone, String disabilityCardNbr, String zipCode, String town, String streetAddress) throws UocSuperMarketOnlineException {
        if ((NIF.isEmpty()) || (name.isEmpty()) || (surname.isEmpty()) || (email.isEmpty()) || (phone.isEmpty()) || (zipCode.isEmpty()) || (town.isEmpty()) || (streetAddress.isEmpty())) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Integer dis = Integer.valueOf(disabilityCardNbr);

            if (findCustomer(NIF) == null) {
                Integer zip = Integer.valueOf(zipCode);
                Location l = new Location(zip, town, streetAddress);
                customers.add(new Disabled(NIF, name, surname, email, phone, dis, l));

            } else {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_ALREADY_EXISTS);
            }


        }
    }

    /**
     * Use case 4 - Creates and Adds a Elderly to the supermarket.
     *
     *
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * CUSTOMER_ALREADY_EXISTS if the customer exists
    
     * @param NIF
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param dateOfBirth
     * @param zipCode
     * @param town
     * @param streetAddress
     * @throws UocSuperMarketOnlineException 
     */
    public void createElderly(String NIF, String name, String surname, String email, String phone, String dateOfBirth, String zipCode, String town, String streetAddress) throws UocSuperMarketOnlineException {
        if ((NIF.isEmpty()) || (name.isEmpty()) || (surname.isEmpty()) || (email.isEmpty()) || (phone.isEmpty()) || (dateOfBirth.isEmpty()) || (zipCode.isEmpty()) || (town.isEmpty()) || (streetAddress.isEmpty())) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Date d = null;
            try {
                d = sdf.parse(dateOfBirth);
            } catch (ParseException e) {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.WRONG_DATE);
            }

            if (findCustomer(NIF) == null) {
                Integer zip = Integer.valueOf(zipCode);
                Location l = new Location(zip, town, streetAddress);
                customers.add(new Elderly(NIF, name, surname, email, phone, d, l));

            } else {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_ALREADY_EXISTS);
            }


        }
    }

    /**
     * Use case 5 - Remove a customer from the supermarket.
     * Product stock is restored for each open order.
     * @param NIF the Nif's customer.
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * CUSTOMER_DOESNT_EXIST if the customer doens't exist
     */
    public void removeCustomer(String NIF) throws UocSuperMarketOnlineException {
        if (NIF.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            //remove Customer
            Customer c = findCustomer(NIF);
            if (c != null) {
            	
            	c.removeAllOrders();
                customers.remove(c);
            } else {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_DOESNT_EXIST);
            }
        }
    }

    /**
     * Use case 6 - Remove an order given its number from the system
     * @param orderNbr the Order's number
     * @throws UocSuperMarketOnlineException:NULL_ARGUMENT if any argument is null
     *  ORDER_DOESNT_EXIST if the order doens't exist
     */
    public void removeOrder(String orderNbr) throws UocSuperMarketOnlineException {
        if (orderNbr.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Integer order = Integer.valueOf(orderNbr);
            Order or = null;
            for (Customer c : customers) {
                or = c.fetchOrder(order);
                if (or != null) {
                    c.removeOrder(or);
                    break;
                }
            }
            if (or == null) {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_DOESNT_EXIST);
            }
        }
    }

    /**
     *  Use case 7 - Remove an product given its ID from its shops.
     * @param idProduct the product's ID
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * PRODUCT_DOESNT_EXIST if the product doens't exist
     * SOME_ORDER_STILL_OPEN if the product is in any opened order
     */
    public void removeProduct(String idProduct) throws UocSuperMarketOnlineException {
        if (idProduct.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Integer product = Integer.valueOf(idProduct);
            
            // Check if the product has still opened orders
            for (Shop s: shops) {
            	for (Product p: s.getProducts()) {
            		if (p.getIdProduct().equals(product)) {
						for (OrderLine ol: p.getOrderlines()) {
							// If there is any opened order with any items throw an exceptions
							if (ol.getOrder().getState() == true && ol.getQuantity() > 0) {
								throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SOME_ORDER_STILL_OPEN);
							}
						}
            		}
            	}
            }         
            
            boolean anyProduct = false;
            for (Shop s : shops) {
                anyProduct = anyProduct || s.removeProduct(product);
                if (anyProduct) break;
            }
            
            //check if found any Product in any Shop
            if (!anyProduct) {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.PRODUCT_DOESNT_EXIST);
            }
            
            // REmove the product from shops
            for (Shop s : shops) {
                s.removeProduct(product);
            }
        }
    }

    /**
     *  Use case 8 - Remove an carrier given its ID from its shops.
     * @param idCarrier The carrier's id.
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     */
    public void removeCarrier(String idCarrier) throws UocSuperMarketOnlineException {
        if (idCarrier.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Integer carrier = Integer.valueOf(idCarrier);
            for (Shop s : shops) {
                s.removeCarrier(carrier);
            }
        }

    }

    /**
     * Use case 9 -  Create a Shop
     * @param idShop shop ID
     * @param zipCode shop ZIP code
     * @param town  shop town
     * @param streetAddress shop street
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * SHOP_ALREADY_EXISTS if the shop already exists
     * LOCATION_HAS_SHOP if location has a shop
     */
    public void createShop(String idShop, String zipCode, String town, String streetAddress) throws UocSuperMarketOnlineException {       
    	 
    	 if ((idShop.isEmpty()) || (zipCode.isEmpty()) || (town.isEmpty()) || (streetAddress.isEmpty())) {
    		 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
	   	 } 
	   	 Integer iIdShop = Integer.valueOf(idShop);
	   	 Integer iZipCode = Integer.valueOf(zipCode);
	   	 
	   	 // Find if shop already exists
	   	 if (findShop(iIdShop) != null) {
	   		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SHOP_ALREADY_EXISTS);
	   	 }
	   	 
	   	 // See if location has already one shop
	   	 Location location = new Location(iZipCode, town, streetAddress);
	   	 if (findShop(location) != null) {
	   		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.LOCATION_HAS_SHOP);
	   	 }
	   	 
	   	 // If we reach here add the Shop
	   	 Shop s = new Shop(iIdShop);
	   	 s.setLocation(location);
	   	 
	   	 shops.add(s);
	   	     	
    }

    /**
     *  Use case 10 - Create a supplier for a shop
     * @param idSupplier supplier id     
     * @param name      supplier name
     * @param NIF       supplier nif
     * @throws UocSuperMarketOnlineException:  NULL_ARGUMENT if any argument is null
     * SHOP_DOESNT_EXIST if shop doesn't exist
     * SUPPLIER_ALREADY_EXISTS if supplier already exist
     */
    public void createSupplier(String idSupplier, String name, String NIF) throws UocSuperMarketOnlineException {
       
    	 if ((idSupplier.isEmpty()) || (name.isEmpty()) || (NIF.isEmpty())) {
    		 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
	   	 } 
	   	 Integer iIdSupplier = Integer.valueOf(idSupplier);
	  
	   	 Supplier supplier; 
	   	 
	   	 if ((supplier = findSupplier(iIdSupplier)) != null) {
	   		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SUPPLIER_ALREADY_EXISTS);
	   	 } else {
	   		supplier = new Supplier(iIdSupplier, name, NIF);
	   		suppliers.add(supplier);
	   	 }
   	 
    }

    /**
     * Use case 11 - Add carrier to a shop
     * @param idCarrier Carrier ID
     * @param idShop ID shop
     * @throws UocSuperMarketOnlineException:  NULL_ARGUMENT if any argument is null
     * SHOP_DOESNT_EXIST if shop doesn't exist
     */
    public void addCarrierToShop(String idCarrier, String idShop) throws UocSuperMarketOnlineException {
        if (idShop.isEmpty() || idCarrier.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Integer ids = Integer.valueOf(idShop);
            Integer idc = Integer.valueOf(idCarrier);
            Shop s = null;
            Carrier c = new Carrier(idc);
            if ((s = findShop(ids)) == null) {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SHOP_DOESNT_EXIST);
            } else {
                s.setCarrier(c);
            }
        }
    }

    /**
     * Use case 12 - List all active orders
     * @return A formatted list of all active orders.   
     */
    public String listActiveOrder() {
        StringBuffer str = new StringBuffer();
        for (Customer c : this.customers) {
            String s = c.printActiveOrders();
            if (!s.equals("")) {
                str.append("Customer: " + c.getNIF() + NL);
                str.append(s);
            }
        }
        return str.toString();
    }

    /**
     *  Use case 13 -  To Add a product to a shop.
     * @param fresh whether Product is fresh or not.
     * @param label Product label
     * @param idProduct Product id
     * @param name Product name
     * @param description Product description
     * @param stock  Prduct stock
     * @param costPrice Product price
     * @param idShop   Shop where product is placed
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * SUPPLIER_DOESNT_EXIST if supplier doens't exist
     * SHOP_DOESNT_EXIST if shop doens't exist
     */
    public void addProductToShop(String fresh, String label, String idProduct, String name, String description, String stock, String costPrice, String idShop, String idSupplier) throws UocSuperMarketOnlineException {
       
    	 if ((fresh.isEmpty()) || (label.isEmpty()) || (idProduct.isEmpty()) || (name.isEmpty()) || (description.isEmpty()) 
    			 || (stock.isEmpty()) || (costPrice.isEmpty()) || (idShop.isEmpty()) || (idSupplier.isEmpty())) {
    		 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
    	 } 
    	 Boolean bFresh = Boolean.valueOf(fresh);
    	 Integer iIdProduct = Integer.valueOf(idProduct);
    	 Integer iStock = Integer.valueOf(stock);
    	 Double dCostPrice = Double.valueOf(costPrice);
    	 Integer iIdShop = Integer.valueOf(idShop);
    	 Integer iIdSupplier = Integer.valueOf(idSupplier);    		 
    	 
    	 //Find the shop
    	 Shop shop;
    	 if ((shop = findShop(iIdShop)) == null) {
    		 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SHOP_DOESNT_EXIST);
    	 }
    	 
    	 // Find supplier
    	 Supplier supplier;
    	 if ((supplier =findSupplier(iIdSupplier)) == null) {
    		 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SUPPLIER_DOESNT_EXIST);
    	 }
    	 
    	 // Create Product
    	 Product product = new Product(bFresh, label, iIdProduct, name, description, iStock, dCostPrice, supplier);
    	 
    	 
    	 //Add the product to the shop
    	 shop.addProduct(product);
    	
    	
    }

    /**
     *  Use case 14 - Add a product to an order. If the order doesn't exist make a new one at date param
     * @param NIF Costumer NIF
     * @param orderNbr Order Number
     * @param date     date of the order
     * @param quantity quantity of product
     * @param idProduct id product
     * @throws UocSuperMarketOnlineException: NULL_ARGUMENT if any argument is null
     * WRONG_DATE if the date was wrong
     * SHOP_DOESNT_EXIST_AT_LOCATION if shop doens't exist in this location
     * ORDER_CLOSED if order is already closed
     * PRODUCT_DOESNT_EXIST if product doesn't exit
     * CUSTOMER_DOESNT_EXIST if customer doesn't exit
     */
    public void addProductToOrder(String NIF, String orderNbr, String date, String quantity, String idProduct) throws UocSuperMarketOnlineException {
       
    	boolean newOrder = false; 
    	
    	if ((NIF.isEmpty()) || (orderNbr.isEmpty()) || (date.isEmpty()) || (quantity.isEmpty()) || (idProduct.isEmpty())) {
    	
    		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
    		
    	 } else {
    		 
             Date d = null;
             try {
                 d = sdf.parse(date);
             } catch (ParseException e) {
                 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.WRONG_DATE);
             }             
             Integer iOrderNbr = Integer.valueOf(orderNbr);
             Integer iQuantity = Integer.valueOf(quantity);
             Integer iIdProduct = Integer.valueOf(idProduct);
             
             
             // Find the customer
             Customer c = findCustomer(NIF);
             if (c == null) {
            
            	 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_DOESNT_EXIST);
            
             } else {
                 // If the order doesn't exist, create it and assign it to this customer
            	 Order order = c.fetchOrder(iOrderNbr);
	             if ((order = c.fetchOrder(iOrderNbr)) == null) { 
	            	 order = new Order(iOrderNbr, d);
	            	 newOrder = true; 
	             }
	             
	             if(order.getState() == false) {
	            	 
	            	 // If the order is closed send exception
	            	 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_CLOSED);
	            	 
	             } else {
	             
		             // Find a shop for this location
		             Shop s;
		             if ((s = findShop(c.getLocation())) == null) {
		            	 
		            	 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SHOP_DOESNT_EXIST_AT_LOCATION);
		            	 
		             } else {
		             
			             // Find product in shop
			             Product product = null;
			             for (Product p: s.getProducts()) {
			            	 if (p.getIdProduct() == iIdProduct) {
			            		 product = p;
			            		 break;
			            	 }
			             }
			             
			             if (product == null) {
			            	 
			            	 throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.PRODUCT_DOESNT_EXIST);
			            	 
			             } else {
			            	 
				             // If the order is new we must assign to the customer
			            	 if (newOrder) {
			            		 c.addOrder(order);
			            	 }
				             product.addToOrder(order, iQuantity);
			             }
		             }
	             }
             }
             
             
    	 }
    	
    }

    /**
     * Use case 15 - List all the orders of one customer given its NIF
     * 
     * @param NIF Customer NIF
     * @return A formatted list of orders for such customer.
     * @throws UocSuperMarketOnlineException: CUSTOMER_DOESNT_EXIST or NULL_ARGUMENT 
     */
    public String listHistoricalOrderbyCustomer(String NIF) throws UocSuperMarketOnlineException {
        StringBuilder str = new StringBuilder();

        if (NIF.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } else {
            Customer c;
            str.append("Customer: ").append(NIF).append(NL);
            if ((c = findCustomer(NIF)) != null) {
                str.append(c.printOrders());
            } else {
                throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_DOESNT_EXIST);
            }
        }

        return str.toString();
    }

    /**
     * Use case 16 - CheckOut given a customer and order number.
     * @param NIF Customer NIF
     * @param orderNbr order number
     * @return A formmatted bill for a such order number and customer.
     * @throws UocSuperMarketOnlineException 
     */
    public String salesCheckCustomer(String NIF, String orderNbr) throws UocSuperMarketOnlineException {
    	
    	Customer c;
    	Order o;
    	
    	if (NIF.isEmpty() || orderNbr.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } 
    	
    	// Get the customer
    	c = findCustomer(NIF);
    	if (c != null) {
    		// Check the customer is not blocked
    		if (c.isBlocked()) {
    			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_IS_BLOCKED);
    		}    		
    	   	// Get the order
	    	o = c.fetchOrder(Integer.valueOf(orderNbr));
	    	if (o == null) {
	    		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_DOESNT_EXIST);
	    	}  
    	} else {
    		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_DOESNT_EXIST);
    	}
    	
    	// If we reach here, we have a valid customer and a valid order
    	
    	// Check the order is not closed
    	/*if (o.getState() == false) {
    		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_CLOSED);
    	} */
    	
    	// Check out the order
    	String ret = c.salesCheck(Integer.valueOf(orderNbr));
    	
    	return ret;
    	
    }

    /**
     * Use case 17 - Close an order given its number and customer NIF.
     * @param NIF Customer nif.
     * @param orderNbr Order Number.
     * @throws UocSuperMarketOnlineException: ORDER_DOESNT_EXIST or CUSTOMER_DOESNT_EXIST or NULL_ARGUMENT 
     *
     */
    public void closeOrder(String NIF, String orderNbr) throws UocSuperMarketOnlineException {
        
    	if (NIF.isEmpty() || orderNbr.isEmpty()) {
            throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.NULL_ARGUMENT);
        } 
    	
    	Customer customer;
    	if ((customer= findCustomer(NIF)) == null) {
    		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.CUSTOMER_DOESNT_EXIST);
    	}
    	
    	Integer iOrderNbr = Integer.valueOf(orderNbr);
    	Order order;
    	
    	if ((order = customer.fetchOrder(iOrderNbr)) == null ) {
    		throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.ORDER_DOESNT_EXIST);
    	}
    	
    	order.closeOrder();
    	
    }

    //UTILITIES
    /**
     * Finds a Customer with the given NIF.  
     * @param NIF - NIF of the customer.
     * @return Customer
     */
    private Customer findCustomer(String NIF) {
        Customer ret = null;
        for (Customer r : this.customers) {
            if (r.getNIF().equalsIgnoreCase(NIF)) {
                ret = (Customer) r;
                break;
            }
        }
        return ret;
    }

    /**
     * Find a Shop by idShop
     * @param idShop
     * @return Shop
     */
    private Shop findShop(Integer idShop) {
        Shop ret = null;
        for (Shop r : this.shops) {
            if (r.getIdShop().equals(idShop)) {
                ret = (Shop) r;
                break;
            }
        }
        return ret;
    }
    
    /**
     * Find a Shop by Location
     * @param location
     * @return Shop
     */
    private Shop findShop(Location location) {
        Shop ret = null;
        for (Shop r : this.shops) {
            if (r.getLocation().equals(location)) {
                ret = (Shop) r;
                break;
            }
        }
        return ret;
    }
    
    /**
     * Find a Supplier by IdSupplier
     * @param IdSupplier
     * @return 
     */
    private Supplier findSupplier(Integer IdSupplier) {
        Supplier ret = null;
        for (Supplier r : this.suppliers) {
            if (r.getIdSupplier().equals(IdSupplier)) {
                ret = (Supplier) r;
                break;
            }
        }
        return ret;
    }
}//uocSuperMarketOnLine
