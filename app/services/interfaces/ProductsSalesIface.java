package services.interfaces;

// Imports
import java.util.*;
import models.*;

public interface ProductsSalesIface {

// Ici service ProductsSales
////

	
	public ProductSale add_product_to_shop( ProductInfo product_info, Float price, Integer stock );
	
	public void remove_product_from_shop( ProductSale product_sale );
	
	public ProductSale edit_product_stock( ProductSale product_sale, Float price, Integer stock );


// public static ProductsSalesIface getInstance();


}

