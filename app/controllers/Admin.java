package controllers;

import models.ProductModel;

import com.avaje.ebean.Ebean;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Admin extends Controller {

	  
	  public static Result delete_product() {
		  
		  
		  String prod_id = Application.get_post("prod_id");
		  
		  if ( prod_id != null ) {

			  ProductModel product = Ebean.find(ProductModel.class).where().eq("id",prod_id).findUnique();
			  
			  if ( product != null ) {
				  product.delete();
			  }
		  
		  }
		  
		  return redirect("/admin");
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
			  return  ok( views.html.admin.render( Application.get_products(), product_form, "Merci de remplir tous les champs") );
	  
		  }
		  
		  /* Ebean se base sur des modeles pour creer automatiquement des BDD interrogeables facon "Objet" */
		  ProductModel prod_already_reg = Ebean.find(ProductModel.class).where().eq("label",product.label).findUnique();

		  if ( prod_already_reg != null ) {
			  return ok(views.html.admin.render( Application.get_products(), product_form, "'"+prod_already_reg.label+"' : Product already registered" ) );
		  }
	  
		  if (product.quantity <= 0) { product.quantity = 0; }
		  product.save();
		  
		  return redirect("/admin");

	  }
	  
	  public static Result admin_page() {
		  
		  return  ok( views.html.admin.render( Application.get_products(), form(ProductModel.class), "") );
		  
	  }
	  
}
