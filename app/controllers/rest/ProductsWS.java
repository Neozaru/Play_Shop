package controllers.rest;


import java.util.Map;

import models.ProductInfoModel;

import org.codehaus.jackson.node.ObjectNode;


import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ProductsWS extends Controller {

	/* Ebauche de WebService. Pour l'exemple, on ne prendra que les operations sur ProductInfo */
	
	public static Result new_product_info() {
		
		System.out.println(request().body().asFormUrlEncoded());
		
		if ( !WSUtils.check_post_data(new String[]{"label","description"}) ) {
			return WSUtils.bad_request_json("Bad arguments");
		}
		else {
			
			ObjectNode result = Json.newObject();
			Map<String,String[]> parameters = request().body().asFormUrlEncoded();

			ProductInfoModel product_info = new ProductInfoModel( parameters.get("label")[0],"");
			product_info.save();

			result.put("product_info", Json.toJson(product_info));

			return WSUtils.ok_request_json( result );		
			
		}
		

	}
	
	
	public static Result get_product_info( Long product_info_id ) {
		

		ProductInfoModel product_info = ProductInfoModel.find.byId(product_info_id);

		if ( product_info == null ) {
			return WSUtils.bad_request_json("Not found");

		}
		else {
			
			ObjectNode result = Json.newObject();

			result.put("product_info", Json.toJson(product_info));
			return WSUtils.ok_request_json( result );

		}
		
	}
	
	public static Result delete_product_info( Long product_info_id ) {
		
		ProductInfoModel product_info = ProductInfoModel.find.byId(product_info_id);

		if ( product_info == null ) {
			return WSUtils.bad_request_json("Not found");

		}
		else {

			ObjectNode result = Json.newObject();

			result.put("product_info", Json.toJson(product_info));
			product_info.delete();
			return WSUtils.ok_request_json( result );

		}
		
	}
	
	
	public static Result update_product_info( Long product_info_id ) {
		
		ProductInfoModel product_info = ProductInfoModel.find.byId(product_info_id);

		if ( product_info == null ) {
			return WSUtils.bad_request_json("Not found");

		}
		else {

			ObjectNode result = Json.newObject();
			Map<String,String[]> parameters = request().body().asFormUrlEncoded();

			
			if ( parameters.containsKey("label") ) {
				product_info.label = parameters.get("label")[0];
			}
			
			if ( parameters.containsKey("description") ) {
				product_info.description = parameters.get("description")[0];
			}
			
			if ( parameters.containsKey("category") ) {
				product_info.category = parameters.get("category")[0];
			}
			/*
			 * Idem for all updatable fields.
			 * We won't use Java reflection (performance issues 1/10)
			 * Moreover, user may wants to remove/add some instructions depending on attribute 
			 */
			
			product_info.save();
			
			result.put("product_info", Json.toJson(product_info));
			return WSUtils.ok_request_json( result );

		}
		
	}
	
	
	public static Result tests() {
		
		return ok( views.html.rest_tests.products_info.render() );
	
	}
	
}
