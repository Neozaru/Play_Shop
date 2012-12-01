
package controllers.rest;

import models.*;

import services.*;

import java.util.Map;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ProductsSalesWS extends Controller {

public static Result add_product_to_shop() {
	
	if ( !WSUtils.check_post_data( new String[]{"price","product_info","stock"}) ) {
			return WSUtils.bad_request_json("Bad arguments");
	}
	
	Long product_info_id = WSUtils.get_post_data_asLong("product_info");
	ProductInfo product_info = ProductInfo.find.byId(product_info_id);
	Float price = WSUtils.get_post_data_asFloat("price");
	Integer stock = WSUtils.get_post_data_asInteger("stock");
	
	

	ProductSale product_sale = Services.getProductsSalesService().add_product_to_shop(product_info,price,stock);

	ObjectNode result = Json.newObject();
	result.put("product_sale", Json.toJson(product_sale));
	
	return WSUtils.ok_request_json( result );

}

public static Result remove_product_from_shop(Long product_sale_id) {
	
	ProductSale product_sale = ProductSale.find.byId(product_sale_id);
	if ( product_sale == null ) {
			return WSUtils.bad_request_json("Required entity not found");
	}

	 Services.getProductsSalesService().remove_product_from_shop(product_sale);

	ObjectNode result = Json.newObject();
	
	return WSUtils.ok_request_json( result );

}

public static Result edit_product_stock(Long product_sale_id) {
	
	ProductSale product_sale = ProductSale.find.byId(product_sale_id);
	if ( product_sale == null ) {
			return WSUtils.bad_request_json("Required entity not found");
	}
	if ( !WSUtils.check_post_data( new String[]{}) ) {
			return WSUtils.bad_request_json("Bad arguments");
	}
	
	Float price = WSUtils.get_post_data_asFloat("price");
	Integer stock = WSUtils.get_post_data_asInteger("stock");
	

	ProductSale product_sale_edited = Services.getProductsSalesService().edit_product_stock(product_sale,price,stock);

	ObjectNode result = Json.newObject();
	result.put("product_sale_edited", Json.toJson(product_sale_edited));
	
	return WSUtils.ok_request_json( result );

}


	public static Result tests() {

		return ok( views.html.rest_tests.ProductsSales_tests.render() );

	}
}

