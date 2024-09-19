package com.fiap.tc.adapters.driver.presentation;

public class URLMapping {

    public static final String ROOT_PRIVATE_API_PATH = "/api/private/v1";
    public static final String ROOT_PUBLIC_API_PATH = "/api/public/v1";
    public static final String ROOT_API_CATEGORIES = ROOT_PRIVATE_API_PATH + "/categories";
    public static final String ROOT_API_HEALTH = ROOT_PUBLIC_API_PATH + "/health";
    public static final String ROOT_PRIVATE_API_CUSTOMERS = ROOT_PRIVATE_API_PATH + "/customers";
    public static final String ROOT_PUBLIC_API_CUSTOMERS = ROOT_PUBLIC_API_PATH + "/customers";
    public static final String ROOT_PRIVATE_API_ORDERS = ROOT_PRIVATE_API_PATH + "/orders";
    public static final String ROOT_PUBLIC_API_ORDERS = ROOT_PUBLIC_API_PATH + "/orders";
    public static final String ROOT_PUBLIC_API_PAYMENTS = ROOT_PUBLIC_API_PATH + "/hook/orders/payments";
    public static final String ROOT_PRIVATE_API_PAYMENTS = ROOT_PRIVATE_API_PATH + "/orders/{id}/payments";
    public static final String ROOT_PRIVATE_API_PRODUCTS = ROOT_PRIVATE_API_PATH + "/products";
    public static final String ROOT_PUBLIC_API_PRODUCTS = ROOT_PUBLIC_API_PATH + "/products";
    public static final String ROOT_PRIVATE_API_PRODUCTS_IMAGES = ROOT_PRIVATE_API_PATH + "/products/images";


}


