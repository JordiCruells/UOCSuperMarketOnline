import java.util.List;
import java.util.ArrayList;
public class Supplier {
	private Integer idSupplier;
	private String name;
	private String NIF;
	private List<Product> products;
	
	/**
	 * Constructor
	 * @param idSupplier, name, NIF & location
	 */
	public Supplier(Integer idSupplier, String name, String NIF) {
		this.idSupplier = idSupplier;
		this.name = name;
		this.NIF = NIF;
		this.products = new ArrayList<Product>();
	}

	/**
	 * Add a product to the list of products for this supplier
	 * @param product
	 */
	public void addProduct(Product product) /*throws UocSuperMarketOnlineException*/  {
		
		/* AQUEST CODI COMENTAT SERIA PER CONTROLAR NO AFEGIR EL MATEIX PRODUCTE DUES
		 * VEGADES A UN SUPPLIER, COM L'ENUNCIAT NO HO DEIA HO HE DEIXAT COMENTAT		 
		if (products.contains(product)) {
			throw new UocSuperMarketOnlineException(UocSuperMarketOnlineException.SUPPLIER_HAS_PRODUCT);
		}
		*/
		products.add(product);	
	}
	
	/**
	 * Print the information related to a supplier.
	 * @return String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Supplier:" + idSupplier);
		
		return sb.toString();
	}
	
	/**
	 * Two suppliers are the same if they have the same idProduct
	 * @param Object
	 */
	public boolean equals(Object o) {
		if (o instanceof Supplier) {
			if (this.getIdSupplier() == ((Supplier)o).getIdSupplier()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return the idSupplier
	 */
	public Integer getIdSupplier() {
		return idSupplier;
	}
}
