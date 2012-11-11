package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/* Pas auto-importe */
import play.data.format.*;
import play.data.validation.*;
/************************/

@Entity
public class ProductSaleModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -231261054250440810L;

	@Id
	@Constraints.Min(10)
	public Long id;
		
	@JoinColumn(name = "ref_product_info", referencedColumnName = "id", insertable = true, updatable = false)
	@OneToOne(optional = false)
	public ProductInfoModel product_info;
	
	public int quantity;

	public float price;
	
	public ProductSaleModel( ProductInfoModel product_info, float price, int quantity ) {
		this.product_info = product_info;
		this.price = price;
		this.quantity = quantity;
		
	}
	
	
	public static Finder<Long,ProductSaleModel> find = new Finder<Long,ProductSaleModel>(
		    Long.class, ProductSaleModel.class
	);
	

	
}
