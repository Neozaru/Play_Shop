package services.implementations;

import services.interfaces.ProductsInfosIface;

// Imports
import java.util.*;
import models.*;


public class ProductsInfosImpl implements ProductsInfosIface {


	@Override
	
	public ProductInfo new_product_info( String label, String description, String category ){
		
		
		// Operation type : constructor
		
			ProductInfo productinfo = new ProductInfo();
			if ( label != null ) {
				productinfo.label = label;
			}
			if ( description != null ) {
				productinfo.description = description;
			}
			if ( category != null ) {
				productinfo.category = category;
			}
			productinfo.save();
			return productinfo;
		
		

	}

	@Override
	
	public ProductInfo edit_product_info( ProductInfo product_info, String label, String description, String category ){
		
		
		// Operation type : setter
		
			if ( label != null ) {
				product_info.label = label;
			}
			if ( description != null ) {
				product_info.description = description;
			}
			if ( category != null ) {
				product_info.category = category;
			}
			product_info.save();
			return product_info;
		
		

	}

	@Override
	
	public void delete_product_info( ProductInfo product_info ){
		
		
		// Operation type : destructor
		
			product_info.delete();
		
		

	}


/*
	public static ProductsInfosIface getInstance() {
	
		if ( _instance == null ) {
			_instance = new ProductsInfosIface();
		}
	
		return _instance;

	}

private static ProductsInfosIface _instance = null;
*/




}

