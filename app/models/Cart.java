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

import models.ProductPurchased;



@Entity
public class Cart extends Model {

@Id
@Constraints.Min(10)
public Long id;


	
	@JoinColumn(name = "productpurchased", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	
	public List<ProductPurchased> products;
	

// Default ctor
public Cart() {}

// Default finder
public static Finder<Long,Cart> find = new Finder<Long,Cart>(
	    Long.class, Cart.class
);

}
