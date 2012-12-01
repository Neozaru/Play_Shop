package services.interfaces;

// Imports
import java.util.*;
import models.*;

public interface ProductsPurchaseIface {

// Ici service ProductsPurchase
////

	
	public void add_product_to_cart( Customer customer, ProductSale product, Integer quantity );
	
	public void remove_product_from_cart( Customer customer, ProductPurchased purchased_product, Integer quantity );


// public static ProductsPurchaseIface getInstance();


}

