
package controllers.rest;

import models.*;

import services.*;

import java.util.Map;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ProductsInfosWS extends Controller {

public static Result new_product_info() {
	
	if ( !WSUtils.check_post_data( new String[]{"category","label","description"}) ) {
			return WSUtils.bad_request_json("Bad arguments");
	}
	
	String label = WSUtils.get_post_data("label");
	String description = WSUtils.get_post_data("description");
	String category = WSUtils.get_post_data("category");
	
	

	ProductInfo product_infos = Services.getProductsInfosService().new_product_info(label,description,category);

	ObjectNode result = Json.newObject();
	result.put("product_infos", Json.toJson(product_infos));
	
	return WSUtils.ok_request_json( result );

}

public static Result edit_product_info(Long product_info_id) {
	
	ProductInfo product_info = ProductInfo.find.byId(product_info_id);
	if ( product_info == null ) {
			return WSUtils.bad_request_json("Required entity not found");
	}
	if ( !WSUtils.check_post_data( new String[]{}) ) {
			return WSUtils.bad_request_json("Bad arguments");
	}
	
	String label = WSUtils.get_post_data("label");
	String description = WSUtils.get_post_data("description");
	String category = WSUtils.get_post_data("category");
	

	ProductInfo product_info_edited = Services.getProductsInfosService().edit_product_info(product_info,label,description,category);

	ObjectNode result = Json.newObject();
	result.put("product_info_edited", Json.toJson(product_info_edited));
	
	return WSUtils.ok_request_json( result );

}

public static Result delete_product_info(Long product_info_id) {
	
	ProductInfo product_info = ProductInfo.find.byId(product_info_id);
	if ( product_info == null ) {
			return WSUtils.bad_request_json("Required entity not found");
	}

	 Services.getProductsInfosService().delete_product_info(product_info);

	ObjectNode result = Json.newObject();
	
	return WSUtils.ok_request_json( result );

}


	public static Result tests() {

		return ok( views.html.rest_tests.ProductsInfos_tests.render() );

	}
}

