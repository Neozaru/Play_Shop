package models;

/* Pas auto-importe */
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.avaje.ebean.Ebean;

import play.data.format.*;
import play.data.validation.*;
/************************/

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import models.ProductInfoModel;

@Entity
public class CartModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1982698773694057243L;

	@Id
	@Constraints.Min(10)
	public Long id;
	
	@JoinColumn(name = "ref_product_info", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	public List<ProductPurchasedModel> purchased_products;


	public CartModel() {
	}
	
	public static Finder<Long,CartModel> find = new Finder<Long,CartModel>(
		    Long.class, CartModel.class
	);
	
	public static Finder<Long,ProductPurchasedModel> find_purchased_products = new Finder<Long,ProductPurchasedModel>(
		    Long.class, ProductPurchasedModel.class
	);
	
	/*
	public CartModel( CustomerModel customer, ProductModel product ) {
		
		this( customer, product, 1 );
		
	}
	
	public CartModel( Long customer_id, Long product_id, int quantity ) {
		
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
		
	}
	
	public CartModel( Long customer_id, Long product_id ) {
		
		this( customer_id, product_id, 1 );
		
	}
	*/
	
	/*
	public void add_to_cart( CustomerModel customer, ProductModel product, int quantity ) {
		
		customer_id = customer.id;
		product_id = product.id;
		this.quantity = quantity;
		
	}
	
	public void add_to_cart( CustomerModel customer, ProductModel product ) {
		
		this.add_to_cart( customer, product, 1 );
		
	}

	
	public List<CartModel> get_products() {
		return get_products_of_customer_id( this.customer_id.toString() );
	}
	
	public static List<CartModel> get_products_of_customer_id( String customer_id ) {
		return Ebean.find(CartModel.class).where().eq("customer_id", customer_id).findList();

	}
	
	*/
	
}
