package com.vttp2022.day21_workshop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Product {

    public static final Logger logger = LoggerFactory.getLogger(Product.class);

    private String productId;
    private String name;
    private String quantity;
    private String unitPrice;
    private String category;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static Product createProduct(SqlRowSet rs) {

        logger.info(">>> inside createProduct fn...");
        logger.info(">>> " + rs.getString("product_id") + " "
                + rs.getString("product_name") + " "
                + rs.getString("quantity") + " "
                + rs.getString("unit_price") + " "
                + rs.getString("category"));

        // logger.info("parseInt >>> " + Integer.parseInt(rs.getString("quantity")));
        // logger.info("parseInt >>> " + Integer.parseInt(rs.getString("unit_price")));

        Product p = new Product();
        p.setProductId(rs.getString("product_id"));
        p.setName(rs.getString("product_name"));
        p.setQuantity(rs.getString("quantity"));
        p.setUnitPrice(rs.getString("unit_price"));
        p.setCategory(rs.getString("category"));

        logger.info(">>> product added to string: " + p.toString());

        return p;
    }

}
