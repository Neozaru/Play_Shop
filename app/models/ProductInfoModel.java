package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity 
public class ProductInfoModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7424627694314358222L;

	@Id
	@Constraints.Min(10)
	public Long id;
		
	@Constraints.Required
	public String label;

	public String description;
	
	public String category;
	
	public ProductInfoModel( String label, String category ) {
		this.label = label;
		this.category = category;
	}

	
	public static Finder<Long,ProductInfoModel> find = new Finder<Long,ProductInfoModel>(
		    Long.class, ProductInfoModel.class
	);
	
	
	
	
}
