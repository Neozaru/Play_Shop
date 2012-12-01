package services.interfaces;

// Imports
import java.util.*;
import models.*;

public interface UserAuthentificationsIface {

// Ici service UserAuthentifications
////

	
	public Boolean register_user( String login, String email, String password, String first_name, String last_name );
	
	public Customer login_user( String login, String password );


// public static UserAuthentificationsIface getInstance();


}

