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




@Entity
public class ProductInfo extends Model {

@Id
@Constraints.Min(10)
public Long id;

	public String label;
	public String description;
	public String category;


// Default ctor
public ProductInfo() {}

// Default finder
public static Finder<Long,ProductInfo> find = new Finder<Long,ProductInfo>(
	    Long.class, ProductInfo.class
);

}
