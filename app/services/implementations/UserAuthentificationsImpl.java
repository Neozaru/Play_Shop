package services.implementations;

import services.interfaces.UserAuthentificationsIface;

// Imports
import java.util.*;

import play.mvc.Controller;
import models.*;
import com.avaje.ebean.Ebean;


public class UserAuthentificationsImpl extends Controller implements UserAuthentificationsIface {


	@Override
	
	public Boolean register_user( String login, String email, String password, String first_name, String last_name ){
		

		// DO NOT REMOVE THIS LINE : Start of user code UserAuthentifications_op_register_user

		Customer cs = new Customer();
		cs.login = login;
		cs.password = password;
		cs.email = email;
		cs.first_name = first_name;
		cs.last_name = last_name;
		
		cs.cart = new Cart();
		cs.save();
		
		return true;

		// STUB : Paste your own code here

		// DO NOT REMOVE THIS LINE : End of user code

		//return null;
		

	}

	@Override
	
	public Customer login_user( String login, String password ){
		

		// DO NOT REMOVE THIS LINE : Start of user code UserAuthentifications_op_login_user
		Customer cs = Ebean.find(Customer.class).where().eq("login", login).eq("password", password).findUnique();
		// STUB : Paste your own code here

		// DO NOT REMOVE THIS LINE : End of user code

		return cs;
		

	}


/*
	public static UserAuthentificationsIface getInstance() {
	
		if ( _instance == null ) {
			_instance = new UserAuthentificationsIface();
		}
	
		return _instance;

	}

private static UserAuthentificationsIface _instance = null;
*/




}

