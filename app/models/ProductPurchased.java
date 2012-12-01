package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity 
public class ProductPurchased extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2090263540544346139L;

	@Id
	@Constraints.Min(10)
	public Long id;
	
	@JoinColumn(name = "ref_product_info", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	public ProductInfo product_info;
	
	
	public float price;
	
	public int quantity;
	
	public ProductPurchased( ProductInfo product_info, float price, int quantity ) {
		this.product_info = product_info;
		this.price = price;
		this.quantity = quantity;
	}

	public static Finder<Long,ProductPurchased> find = new Finder<Long,ProductPurchased>(
		    Long.class, ProductPurchased.class
	);
	
}
