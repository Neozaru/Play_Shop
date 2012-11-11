package controllers;

import models.CartModel;
import models.CustomerModel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import structures.CustomerForm;

import com.avaje.ebean.Ebean;

public class Accounts extends Controller {

    
	  
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

		  customer.cart = new CartModel();
		  customer.cart.save();

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
	  
	  return redirect("/shop");
	 
	  
  }
  
  
  public static Result logout() {
	  session().clear();
	  
	  return redirect("/shop");
  }
  
}
