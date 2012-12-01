package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Cart;
import models.Customer;
import models.ProductInfo;
import models.ProductSale;
import play.*;
import play.data.Form;
import play.libs.F.Tuple;
import play.mvc.*;
import structures.CustomerForm;

import views.html.*;

public class Application extends Controller {
  
	
    private Customer _connected_customer;
    
    public static Result index() {
    return ok(index.render("Your new application2 is ready."));
  }
  
  private static Customer connected_customer() {
	  
	  if ( session().containsKey("user_id")) {
		  return Ebean.find(Customer.class).where().eq("id",session().get("user_id")).findUnique();
	  }
	  
	  return null;
	  
  }
  
  public static List<ProductSale> get_product_sales( Long id_product_info ) {

	  List<ProductSale> products_list =Ebean.find(ProductSale.class).where().eq("ref_product_info", id_product_info.toString()).findList();
	  return products_list;
  
  }
  
  public static List<ProductSale> get_products_sales() {
	  List<ProductSale> products_list = Ebean.find(ProductSale.class).findList();

	  return products_list;
  }
  
  public static ProductInfo get_product_info( Long id ) {
	  ProductInfo product_info = Ebean.find(ProductInfo.class).where().eq("id",id.toString()).findUnique();
	  return product_info;
  }
  
  public static List<ProductInfo> get_products_infos() {
	  List<ProductInfo> products_list = Ebean.find(ProductInfo.class).findList();
	  return products_list;
  }
  
  public static String get_post( String key ) {
	  
	  String[] post = request().body().asFormUrlEncoded().get( key );
	  if ( post != null && post.length > 0 ) {
		  return post[0];
	  }
	  
	  return null;
	  
  }
  
  private static String connected_customer_login() {
	  
	  if ( session().containsKey("user_login") ) {
		  return session().get("user_login");
	  }
	  
	  return null;
	  
  }
  
  public static Result shop() {

	  /* Ebean se base sur des modeles pour creer automatiquement des BDD interrogeables facon "Objet" */
	  System.out.println("LOL");
	  
	  return ok(views.html.shop.render( connected_customer_login(), get_products_sales() ) );
	  
  }

  
  public static Result cart() {
	  
	  Cart cart_model = null;
	  
	  Customer user_model = connected_customer();
	  
	  if ( user_model != null ) {
		  cart_model = user_model.cart;
	  }
	
	  return ok( views.html.cart.render( cart_model ) );
	  
  }
 
  
 
  
}