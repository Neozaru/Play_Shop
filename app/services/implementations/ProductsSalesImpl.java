package services.implementations;

import services.interfaces.ProductsSalesIface;

// Imports
import java.util.*;
import models.*;


public class ProductsSalesImpl implements ProductsSalesIface {


	@Override
	
	public ProductSale add_product_to_shop( ProductInfo product_info, Float price, Integer stock ){
		
		
		// Operation type : constructor
		
			ProductSale productsale = new ProductSale();
			if ( product_info != null ) {
				productsale.product_info = product_info;
			}
			if ( price != null ) {
				productsale.price = price;
			}
			if ( stock != null ) {
				productsale.stock = stock;
			}
			productsale.save();
			return productsale;
		
		

	}

	@Override
	
	public void remove_product_from_shop( ProductSale product_sale ){
		
		
		// Operation type : destructor
		
			product_sale.delete();
		
		

	}

	@Override
	
	public ProductSale edit_product_stock( ProductSale product_sale, Float price, Integer stock ){
		
		
		// Operation type : setter
		
			if ( price != null ) {
				product_sale.price = price;
			}
			if ( stock != null ) {
				product_sale.stock = stock;
			}
			product_sale.save();
			return product_sale;
		
		

	}


/*
	public static ProductsSalesIface getInstance() {
	
		if ( _instance == null ) {
			_instance = new ProductsSalesIface();
		}
	
		return _instance;

	}

private static ProductsSalesIface _instance = null;
*/




}

