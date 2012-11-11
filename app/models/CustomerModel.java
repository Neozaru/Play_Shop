package models;

import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Constraint;

/* Pas auto-importe */
import play.data.format.*;
import play.data.validation.*;
/************************/

import play.db.ebean.*;
import structures.CustomerForm;

@Entity 
public class CustomerModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5524409000435734556L;

	public static int PASSWORD_MIN_VALUE = 4;

	/** STORED **/
	
	@Id
	@Constraints.Min(10)
	public Long id;
	
	@Constraints.Required
	public String login;
	
	@Constraints.Email
	@Constraints.Required
	public String email;
	
	@Constraints.Required
	public String password; /* Bad example */
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date dueDate = new Date();
	
	@JoinColumn(name = "ref_cart", referencedColumnName = "id", insertable = true, updatable = false)
	@OneToOne(optional = false)
	public CartModel cart;
	
	/** END STORED **/

	
	public static Finder<Long,CustomerModel> find = new Finder<Long,CustomerModel>(
		    Long.class, CustomerModel.class
	);
	
	public CustomerModel(String login, String email, String password) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.cart = new CartModel();
	}

	public static CustomerModel from_form( CustomerForm form ) {
		
		return new CustomerModel( form.login, form.email, form.password );
		
	}

	/**
	 * Used during login. Checks in password is correct
	 * @return
	 */
	public boolean check_password() {
		return ( true );
	}

}
