package services;

import services.interfaces.*;
import services.implementations.*;

public class Services {

// UserAuthentifications
public static UserAuthentificationsIface getUserAuthentificationsService() {
	return new UserAuthentificationsImpl();
}

// ProductsSales
public static ProductsSalesIface getProductsSalesService() {
	return new ProductsSalesImpl();
}

// ProductsInfos
public static ProductsInfosIface getProductsInfosService() {
	return new ProductsInfosImpl();
}

// ProductsPurchase
public static ProductsPurchaseIface getProductsPurchaseService() {
	return new ProductsPurchaseImpl();
}

// CustomerCart
public static CustomerCartIface getCustomerCartService() {
	return new CustomerCartImpl();
}

// ProductsPublicInterface
public static ProductsPublicInterfaceIface getProductsPublicInterfaceService() {
	return new ProductsPublicInterfaceImpl();
}


}

