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

import models.ProductInfo;



@Entity
public class ProductPurchased extends Model {

@Id
@Constraints.Min(10)
public Long id;

	public Float price;
	public Integer quantity;

	
	@JoinColumn(name = "productinfo", referencedColumnName = "id", insertable = true, updatable = false)
	
	public ProductInfo product_info;
	

// Default ctor
public ProductPurchased() {}

// Default finder
public static Finder<Long,ProductPurchased> find = new Finder<Long,ProductPurchased>(
	    Long.class, ProductPurchased.class
);



}
