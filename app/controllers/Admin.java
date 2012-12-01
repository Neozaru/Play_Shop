package controllers;

import java.util.List;

import models.ProductInfo;
import models.ProductSale;

import com.avaje.ebean.Ebean;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Admin extends Controller {

	  

	  public static Result admin_page() {
		  
		  return  ok( views.html.admin_product_infos.render( Application.get_products_infos(), form(ProductInfo.class), "") );
		  
	  }

	  /*** PRODUCT INFOS ***/
	  
	  public static Result add_product_info() {
		  
		  Form<ProductInfo> product_info_form = form(ProductInfo.class);
		  
		  ProductInfo product = null;
		  if ( product_info_form != null )  {
			  
			  try {
				  product = product_info_form.bindFromRequest().get();
			  }
			  catch( IllegalStateException e ) {
				  product = null;
			  }
		  }
		  
		  if ( product == null ) {
			  return  ok( views.html.admin_product_infos.render( Application.get_products_infos(), product_info_form, "merci de remplir tous les champs") );
	  
		  }
		  
		  
		  /* Ebean se base sur des modeles pour creer automatiquement des BDD interrogeables facon "Objet" */
		  
		  ProductInfo prod_already_reg = Ebean.find(ProductInfo.class).where().eq("label",product.label).findUnique();

		  if ( prod_already_reg != null ) {
			  return ok(views.html.admin_product_infos.render( Application.get_products_infos(), product_info_form, "'"+prod_already_reg.label+"' : Product already registered" ) );
		  }
		  
	  
		  product.save();
		  
		  return redirect("/admin");

	  }
	  
	  public static Result delete_product_info() {
		  
		  
		  String prod_id = Application.get_post("prod_id");
		  
		  if ( prod_id != null ) {

			  ProductInfo product = Ebean.find(ProductInfo.class).where().eq("id",prod_id).findUnique();
			  
			  if ( product != null ) {
				  product.delete();
			  }
		  
		  }
		  
		  return redirect("/admin");
	  }
	  

	  
	  /*** PRODUCT SALES ***/
	  
	  public static Result admin_sales_page( Long product_info_id ) {
		  	
		  System.out.println("--- Id : "+product_info_id);
		  List<ProductSale> product_sales = Application.get_product_sales(product_info_id);
		  
		  
		  
		  return ok( views.html.admin_product_sales.render( Application.get_product_info(product_info_id), form(ProductSale.class), "" ));
		  
	  }
	  
	  public static Result add_product_sale( Long product_info_id ) {
		  
		  ProductInfo product_info = Application.get_product_info(product_info_id);
		  
		  Form<ProductSale> product_sale_form = form(ProductSale.class);
		  
		  ProductSale product_sale = null;
		  if ( product_sale_form != null )  {
			  
			  try {
				  product_sale = product_sale_form.bindFromRequest().get();
			  }
			  catch( IllegalStateException e ) {
				  product_sale = null;
			  }
		  }
		  
		  if ( product_sale == null ) {
			  return  ok( views.html.admin_product_sales.render( product_info, product_sale_form, "Merci de remplir tous les champs") );
	  
		  }
		  
		  System.out.println("Product Info : "+product_info.id);
	

		  product_sale.product_info = product_info;
		  product_sale.save();
		  
		  return redirect("../product_sales/"+product_info_id);
	  }
	  
	  public static Result delete_product_sale( Long product_info_id ) {
		  
		  
		  String prod_id = Application.get_post("prod_sale_id");
		  
		  if ( prod_id != null ) {

			  ProductSale product = Ebean.find(ProductSale.class).where().eq("id",prod_id).findUnique();
			  
			  if ( product != null ) {
				  product.delete();
			  }
		  
		  }
		  
		  return redirect("../product_sales/"+product_info_id);
	  }
	  
	  
	  

	  
}
