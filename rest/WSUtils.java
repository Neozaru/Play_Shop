
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

	public static String get_post_data( String key ) {

		Map<String,String[]> post_data_or = request().body().asFormUrlEncoded();
		if ( post_data_or.containsKey(key) ) {
			return post_data_or.get(key)[0];
		}

		return null;
	}



	public static Float get_post_data_asFloat( String key ) {

		String str = get_post_data(key);
		
		if ( str != null ) {
			return Float.parseFloat(str);
		}
		else {
			return null;
		}

	}
	public static Double get_post_data_asDouble( String key ) {

		String str = get_post_data(key);
		
		if ( str != null ) {
			return Double.parseDouble(str);
		}
		else {
			return null;
		}

	}
	public static Long get_post_data_asLong( String key ) {

		String str = get_post_data(key);
		
		if ( str != null ) {
			return Long.parseLong(str);
		}
		else {
			return null;
		}

	}
	public static Integer get_post_data_asInteger( String key ) {

		String str = get_post_data(key);
		
		if ( str != null ) {
			return Integer.parseInt(str);
		}
		else {
			return null;
		}

	}




}

