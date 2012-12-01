package services.interfaces;

// Imports
import java.util.*;
import models.*;

public interface ProductsInfosIface {

// Ici service ProductsInfos
////

	
	public ProductInfo new_product_info( String label, String description, String category );
	
	public ProductInfo edit_product_info( ProductInfo product_info, String label, String description, String category );
	
	public void delete_product_info( ProductInfo product_info );


// public static ProductsInfosIface getInstance();


}

