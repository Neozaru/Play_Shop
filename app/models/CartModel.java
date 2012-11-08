package models;

/* Pas auto-importe */
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Ebean;

import play.data.format.*;
import play.data.validation.*;
/************************/

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import models.ProductModel;

@Entity
public class CartModel extends Model {

	@Id
	@Constraints.Min(10)
	public Long id;
	
	public Long customer_id;
	
	public Long product_id;

	public int quantity;
	
	
	
	
	public static Finder<Long,CartModel> find = new Finder<Long,CartModel>(
		    Long.class, CartModel.class
	);
	
	public CartModel() {
		
	}
	
	public CartModel( CustomerModel customer, ProductModel product, int quantity ) {
		
		customer_id = customer.id;
		product_id = product.id;
		this.quantity = quantity;
		
	}
	
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
	
	public void add_to_cart( CustomerModel customer, ProductModel product, int quantity ) {
		
		customer_id = customer.id;
		product_id = product.id;
		this.quantity = quantity;
		
	}
	
	public void add_to_cart( CustomerModel customer, ProductModel product ) {
		
		this.add_to_cart( customer, product, 1 );
		
	}
	
	public ProductModel get_product() {
		return Ebean.find(ProductModel.class).where().eq("id", product_id).findUnique(); 
	}
	
	
	public List<CartModel> get_products() {
		return get_products_of_customer_id( this.customer_id.toString() );
	}
	
	public static List<CartModel> get_products_of_customer_id( String customer_id ) {
		return Ebean.find(CartModel.class).where().eq("customer_id", customer_id).findList();

	}
	
}
