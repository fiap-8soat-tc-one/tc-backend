package com.fiap.tc.adapter.web;

public class URLMapping {

    public static final String ROOT_PRIVATE_API_PATH = "/api/private/v1";
    public static final String ROOT_PUBLIC_API_PATH = "/api/public/v1";
    public static final String ROOT_API_CATEGORIES = ROOT_PRIVATE_API_PATH + "/categories";
    public static final String ROOT_API_HEALTH = ROOT_PUBLIC_API_PATH + "/health";
    public static final String ROOT_PRIVATE_API_CUSTOMERS = ROOT_PRIVATE_API_PATH + "/customers";
    public static final String ROOT_PUBLIC_API_CUSTOMERS = ROOT_PUBLIC_API_PATH + "/customers";
    public static final String ROOT_PRIVATE_API_ORDERS = ROOT_PRIVATE_API_PATH + "/orders";
    public static final String ROOT_PUBLIC_API_ORDERS = ROOT_PUBLIC_API_PATH + "/orders";
    public static final String ROOT_PUBLIC_API_PAYMENT = ROOT_PUBLIC_API_PATH + "/hook/orders/payment";
}


