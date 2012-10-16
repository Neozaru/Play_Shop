package models;

import javax.persistence.Entity;
import javax.persistence.Id;


import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/* Pas auto-importe */
import play.data.format.*;
import play.data.validation.*;
/************************/

@Entity
public class ProductModel extends Model {

	@Id
	@Constraints.Min(10)
	public Long id;
		
	@Constraints.Required
	public String label;

	public int quantity; // Normally, in another table
	
	public String category;
	
	
	public ProductModel( String label, String category, int quantity ) {
		this.label = label;
		this.category = category;
		this.quantity = quantity;
	}
	
	
	public static Finder<Long,ProductModel> find = new Finder<Long,ProductModel>(
		    Long.class, ProductModel.class
	);
	

	
}
