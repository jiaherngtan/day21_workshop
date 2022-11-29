package com.vttp2022.day21_workshop.repository;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.vttp2022.day21_workshop.model.Customer;
import com.vttp2022.day21_workshop.model.Order;
import com.vttp2022.day21_workshop.model.Product;

import static com.vttp2022.day21_workshop.repository.Queries.*;

@Repository
public class CustomerRepository {

    public static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getCustomerList(int offset, int limit) {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(
                SQL_GET_CUSTOMERS, limit, offset);

        final List<String> customersName = new LinkedList<>();

        while (rs.next()) {
            Customer c = Customer.createCustomer(rs);
            customersName.add(c.getFirstName() + "-" + c.getLastName());
        }

        return customersName;
    }

    public Customer getCustomerDetails(String customerId) {

        return jdbcTemplate.queryForObject(SQL_GET_CUSTOMERS_DETAILS, new Customer(), Integer.parseInt(customerId));

    }

    public List<Order> getCustomerOrders(String customerId) {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(
                SQL_GET_CUSTOMERS_ORDERS, Integer.parseInt(customerId));

        List<Order> orders = new LinkedList<>();

        // logger.info(">>> order has next? " + rs.next());

        // rs.previous();
        while (rs.next()) {
            List<Product> products = getCustomerOrdersDetails(rs.getString("id"));
            orders.add(Order.createOrder(rs, products));
        }

        return orders;

    }

    public List<Product> getCustomerOrdersDetails(String orderId) {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(
                SQL_GET_CUSTOMERS_ORDERS_DETAILS, Integer.parseInt(orderId));

        List<Product> products = new LinkedList<>();

        while (rs.next())
            products.add(Product.createProduct(rs));

        return products;

    }

}
