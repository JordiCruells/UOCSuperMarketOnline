
/**
 * Exception class, with all the messages of UocSuperMarketOnline.
 * 
 * @author OOP teaching staff
 * @version 
 * @since 
 */
public class UocSuperMarketOnlineException extends Exception {

    /**
     * Customer already exists 
     */
    public static final String CUSTOMER_ALREADY_EXISTS = "-ERROR: CUSTOMER ALREADY EXISTS";
    //
    public static final String SUPPLIER_ALREADY_EXISTS = "-ERROR: SUPPLIER ALREADY EXISTS";
    //
    public static final String SHOP_ALREADY_EXISTS = "-ERROR: SHOP ALREADY EXISTS";
    //
    public static final String LOCATION_HAS_SHOP = "-ERROR: LOCATION HAS SHOP";
    //
    public static final String CUSTOMER_DOESNT_EXIST = "-ERROR: CUSTOMER DOESNT EXIST";
    public static final String CARRIER_DOESNT_EXIST = "-ERROR: CARRIER DOESNT EXIST";        
    //
    public static final String ORDER_DOESNT_EXIST = "-ERROR: ORDER DOESNT EXIST";
    //
    public static final String NOT_ENOUGHT_STOCK = "-ERROR: NOT ENOUGHT STOCK";
    //
    public static final String ORDER_CLOSED = "-ERROR: ORDER CLOSED";
    public static final String SOME_ORDER_STILL_OPEN = "-ERROR: SOME ORDER STILL OPEN";
    /**
     * Null argument
     */
    public static final String NULL_ARGUMENT = "-ERROR: ARGUMENT IS NULL";
    /**
     * Wrong date 
     */
    public static final String WRONG_DATE = "-ERROR: WRONG DATE";
    //
    public static final String LOCATION_DOESNT_EXIST = "-ERROR: LOCATION DOESNT EXIST";
    //
    public static final String SHOP_DOESNT_EXIST = "-ERROR: SHOP DOESNT EXIST";
    //
    public static final String SHOP_DOESNT_EXIST_AT_LOCATION = "-ERROR: SHOP DOESNT EXIST AT LOCATION";
    //
    public static final String PRODUCT_DOESNT_EXIST = "-ERROR: PRODUCT DOESNT EXIST";
    //
    public static final String SUPPLIER_DOESNT_EXIST = "-ERROR: SUPPLIER DOESNT EXIST";
    //
    public static final String LOCATION_ALREADY_EXISTS = "-ERROR: LOCATION ALREADY EXISTS";
    //
    public static final String SHOP_HAS_PRODUCT = "-ERROR: SHOP HAS PRODUCT";

    
    
    
    public static final String CUSTOMER_IS_BLOCKED = "-ERROR: CUSTOMER IS BLOCKED";
    
    
    
    
    /**
     * Constructs a new UocSuperMarketOnline exception with the specified detail message and cause.<br />
     * Note that the detail message associated with cause is not automatically incorporated in this exception's detail message.
     * @param message The detail message (which is saved for later retrieval by the #getMessage() method).
     * @param cause The cause (which is saved for later retrieval by the #getCause() method). (A null value is permitted, and indicates that the cause is
     */
    public UocSuperMarketOnlineException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UocSuperMarketOnline exception with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to #initCause
     * @param message The detail message. It's saved for later retrieval by the #getMessage() method.
     */
    public UocSuperMarketOnlineException(String message) {
        super(message);
    }
}//UocSuperMarketOnlineException