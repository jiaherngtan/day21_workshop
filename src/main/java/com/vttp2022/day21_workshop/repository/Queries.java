package com.vttp2022.day21_workshop.repository;

public class Queries {

    public static final String SQL_GET_CUSTOMERS = "SELECT first_name,last_name FROM customers LIMIT ? OFFSET ?";

    public static final String SQL_GET_CUSTOMERS_DETAILS = "SELECT * FROM customers WHERE id = ?";

    public static final String SQL_GET_CUSTOMERS_ORDERS = "SELECT * FROM orders WHERE customer_id = ?";

    public static final String SQL_GET_CUSTOMERS_ORDERS_DETAILS = "SELECT * FROM orders JOIN order_details ON orders.id = order_details.order_id JOIN products ON order_details.product_id = products.id WHERE orders.id = ?";

}
