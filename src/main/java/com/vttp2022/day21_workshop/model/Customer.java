package com.vttp2022.day21_workshop.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer implements RowMapper<Customer> {

    private String id;
    private String firstName;
    private String lastName;
    private String company;
    private String jobTitle;
    private String businessPhone;
    private String address;
    private String city;
    private String stateProvince;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public static Customer createCustomer(SqlRowSet rs) {

        Customer c = new Customer();
        c.setId(rs.getString("id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setCompany(rs.getString("company"));
        c.setJobTitle(rs.getString("job_title"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setStateProvince(rs.getString("state_province"));

        return c;
    }

    @Override
    public Customer mapRow(ResultSet rs, int row) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getString("id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setCompany(rs.getString("company"));
        c.setJobTitle(rs.getString("job_title"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setStateProvince(rs.getString("state_province"));

        return c;
    }

    public JsonObject toJson() {

        return Json.createObjectBuilder()
                .add("id", getId())
                .add("first_name", getFirstName())
                .add("last_name", getLastName())
                .add("company", getCompany())
                .add("job_title", getJobTitle())
                .add("business_phone", getBusinessPhone())
                .add("address", getAddress())
                .add("city", getCity())
                .add("state_province", getStateProvince())
                .build();
    }

}
