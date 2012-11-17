package controllers.rest;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class WSUtils extends Controller {

	public static Result bad_request_json( String error_message ) {
		
		ObjectNode result = Json.newObject();

		result.put("result","BAD");
		result.put("message",error_message);
		
		return badRequest( result );
		
	}
	
	public static Result ok_request_json( JsonNode jsonNode ) {
		
		ObjectNode result = Json.newObject();
		result.put("result","OK");
		result.put("content",jsonNode);
		
		return ok(result);
		
	}
	
	public static boolean check_post_data( String[] required_fields ) {
		
		Map<String,String[]> post_data_or = request().body().asFormUrlEncoded();
		for ( String f : required_fields ) {
			if ( !post_data_or.containsKey(f) ) {
				return false;
			}
		}
		
		return true;
		
	}
	

}
