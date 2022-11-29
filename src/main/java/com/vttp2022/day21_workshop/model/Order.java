package com.vttp2022.day21_workshop.model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Order {

    private String orderId;
    private String employeeId;
    private String customerId;
    private String orderDate;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private List<Product> products = new LinkedList<>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", employeeId=" + employeeId + ", customerId=" + customerId
                + ", orderDate=" + orderDate + ", shipName=" + shipName + ", shipAddress=" + shipAddress + ", shipCity="
                + shipCity + ", products=" + products + "]";
    }

    public static Order createOrder(SqlRowSet rs, List<Product> products) {

        Order o = new Order();
        o.setOrderId(rs.getString("id"));
        o.setEmployeeId(rs.getString("employee_id"));
        o.setCustomerId(rs.getString("customer_id"));
        o.setOrderDate(rs.getString("order_date"));
        o.setShipName(rs.getString("ship_name"));
        o.setShipAddress(rs.getString("ship_address"));
        o.setShipCity(rs.getString("ship_city"));
        o.setProducts(products);

        return o;
    }

    public JsonObject toJson() {

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Product p : products) {
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            objBuilder.add("product_id", p.getProductId());
            objBuilder.add("product_name", p.getName());
            objBuilder.add("quantity", p.getQuantity());
            objBuilder.add("unit_price", p.getUnitPrice());
            objBuilder.add("category", p.getCategory());
            arrBuilder.add(objBuilder);
        }
        JsonArray productsObj = arrBuilder.build();

        return Json.createObjectBuilder()
                .add("id", getOrderId())
                .add("employee_id", getEmployeeId())
                .add("customer_id", getCustomerId())
                .add("order_date", getOrderDate())
                .add("ship_name", getShipName())
                .add("ship_address", getShipAddress())
                .add("ship_city", getShipCity())
                .add("products", productsObj)
                .build();
    }

}
