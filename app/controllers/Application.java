package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.CartModel;
import models.CustomerModel;
import models.ProductInfoModel;
import models.ProductSaleModel;
import play.*;
import play.data.Form;
import play.libs.F.Tuple;
import play.mvc.*;
import structures.CustomerForm;

import views.html.*;

public class Application extends Controller {
  
	
    private CustomerModel _connected_customer;
    
    public static Result index() {
    return ok(index.render("Your new application2 is ready."));
  }
  
  private static CustomerModel connected_customer() {
	  
	  if ( session().containsKey("user_id")) {
		  return Ebean.find(CustomerModel.class).where().eq("id",session().get("user_id")).findUnique();
	  }
	  
	  return null;
	  
  }
  
  public static List<ProductSaleModel> get_product_sales( Long id_product_info ) {

	  List<ProductSaleModel> products_list =Ebean.find(ProductSaleModel.class).where().eq("ref_product_info", id_product_info.toString()).findList();
	  return products_list;
  
  }
  
  public static List<ProductSaleModel> get_products_sales() {
	  List<ProductSaleModel> products_list = Ebean.find(ProductSaleModel.class).findList();

	  return products_list;
  }
  
  public static ProductInfoModel get_product_info( Long id ) {
	  ProductInfoModel product_info = Ebean.find(ProductInfoModel.class).where().eq("id",id.toString()).findUnique();
	  return product_info;
  }
  
  public static List<ProductInfoModel> get_products_infos() {
	  List<ProductInfoModel> products_list = Ebean.find(ProductInfoModel.class).findList();
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
	  
	  CartModel cart_model = null;
	  
	  CustomerModel user_model = connected_customer();
	  
	  if ( user_model != null ) {
		  cart_model = user_model.cart;
	  }
	
	  return ok( views.html.cart.render( cart_model ) );
	  
  }
 
  
 
  
}