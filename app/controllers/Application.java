package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

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
    return ok(index.render("Your new application is ready."));
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
    
  
  public static Result register_page() {
	  return ok(views.html.register.render( form(CustomerForm.class), "" ) );
  }
  
  
  public static Result register_action() {
	  
	  /* Play fournit un panoplie d'outils pour gerer automatiquement les forms */
	  Form<CustomerForm> customerForm = form(CustomerForm.class);
	  
	  
	  CustomerForm cf = customerForm.bindFromRequest().get();

	  /* Ebean se base sur des modeles pour creer automatiquement des BDD interrogeables facon "Objet" */
	  CustomerModel cm_already_reg = Ebean.find(CustomerModel.class).where().eq("login",cf.login).findUnique();

	  if ( cm_already_reg != null ) {
		  return ok(views.html.register.render( customerForm, "'"+cm_already_reg.login+"' : Username already registered" ) );
	  }
	  
	  if ( cf.check_password_confirmation() ) {
		  
		  CustomerModel customer = CustomerModel.from_form(cf);
		  customer.save();
		  
		  
		  return ok(views.html.register_ok.render( customer ) );
	  }
	  else {
		  return ok(views.html.register.render( customerForm, "Les deux mots de passe ne correspondent pas" ) );
	  }
  }
  
  public static Result login_page() {
	  return ok(views.html.login.render( form(CustomerForm.class), "" ));
  }
  
  public static Result logout() {
	  session().clear();
	  
	  return redirect("shop");
  }
  
  public static Result login_action() {
	  	  
	  Form<CustomerForm> customerForm = form(CustomerForm.class);
	  CustomerForm cf = customerForm.bindFromRequest().get();
	  
	  /* Ebean se base sur des modeles pour creer automatiquement des BDD interrogeables facon "Objet" */
	  CustomerModel customer = Ebean.find(CustomerModel.class).where().eq("login",cf.login).eq("password", cf.password).findUnique();
	  
	  if ( customer == null ) {
		  return ok(views.html.login.render( customerForm, "Incorrect user login or/and password" ) ); 
	  }
	  
	  session().put( "user_id", customer.id.toString() );
	  session().put( "user_login", customer.login );
	  
	  return redirect("shop");
	 
	  
  }
  
  
  
  public static Result delete_product() {
	  
	  
	  String prod_id = get_post("prod_id");
	  
	  if ( prod_id != null ) {

		  ProductModel product = Ebean.find(ProductModel.class).where().eq("id",prod_id).findUnique();
		  
		  if ( product != null ) {
			  product.delete();
		  }
	  
	  }
	  
	  return redirect("admin");
  }
  
  public static Result add_product(  ) {
	  
	  Form<ProductModel> product_form = form(ProductModel.class);
	  
	  ProductModel product = null;
	  if ( product_form != null )  {
		  
		  try {
			  product = product_form.bindFromRequest().get();
		  }
		  catch( IllegalStateException e ) {
			  product = null;
		  }
	  }
	  
	  if ( product == null || product.label.isEmpty() ) {
		  return  ok( views.html.admin.render( get_products(), product_form, "Merci de remplir tous les champs") );
  
	  }
	  
	  /* Ebean se base sur des modeles pour creer automatiquement des BDD interrogeables facon "Objet" */
	  ProductModel prod_already_reg = Ebean.find(ProductModel.class).where().eq("label",product.label).findUnique();

	  if ( prod_already_reg != null ) {
		  return ok(views.html.admin.render( get_products(), product_form, "'"+prod_already_reg.label+"' : Product already registered" ) );
	  }
  
	  if (product.quantity <= 0) { product.quantity = 0; }
	  product.save();
	  
	  return redirect("admin");

  }
  
  public static Result admin_page() {
	  
	  return  ok( views.html.admin.render( get_products(), form(ProductModel.class), "") );
	  
  }
  
 
  
}