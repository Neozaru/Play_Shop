package models;



// Imports
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.validation.Constraint;

import com.avaje.ebean.Ebean;

import play.data.format.*;
import play.data.validation.*;
/************************/

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import models.Cart;



@Entity
public class Customer extends Model {

@Id
@Constraints.Min(10)
public Long id;

	@Constraints.Required
	public String login;
	@Constraints.Email
	@Constraints.Required
	public String email;
	@Constraints.Required
	public String password;
	public Date registration_date;
	public String first_name;
	public String last_name;

	
	@JoinColumn(name = "cart", referencedColumnName = "id", insertable = true, updatable = false)
	
	public Cart cart;
	

// Default ctor
public Customer() {}

// Default finder
public static Finder<Long,Customer> find = new Finder<Long,Customer>(
	    Long.class, Customer.class
);

}
