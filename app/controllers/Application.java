package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.CartModel;
import models.CustomerModel;
import models.ProductModel;
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
	  
	  //session()
	  return new CustomerModel(null, null, null); // Stub
	  
	  
  }
  
  public static List<ProductModel> get_products() {
	  List<ProductModel> products_list = Ebean.find(ProductModel.class).findList();
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

	  
	  return ok(views.html.shop.render( connected_customer_login(), get_products() ) );
	  
  }

  
  public static Result cart() {
	  
	  List<CartModel> cart_model = null;
	  
	  if ( session().containsKey("user_id") ) {
		  cart_model = CartModel.get_products_of_customer_id( session().get("user_id") );
	  }
	  
	  return ok( views.html.cart.render( cart_model ) );
	  
  }
 
  
 
  
}