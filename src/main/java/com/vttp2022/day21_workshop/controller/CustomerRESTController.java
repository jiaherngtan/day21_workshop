package com.vttp2022.day21_workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vttp2022.day21_workshop.model.Customer;
import com.vttp2022.day21_workshop.model.Order;
import com.vttp2022.day21_workshop.repository.CustomerRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(path = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRESTController {

    @Autowired
    private CustomerRepository cr;

    @GetMapping
    public ResponseEntity<String> getCustomers() {

        List<String> customersName = cr.getCustomerList(0, 5);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customersName.toString());
    }

    @GetMapping(path = "{offset}/{limit}")
    public ResponseEntity<String> getCustomers(
            @PathVariable int offset,
            @PathVariable int limit) {

        List<String> customersName = cr.getCustomerList(offset, limit);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customersName.toString());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<String> getCustomerDetails(
            @PathVariable String id) {

        try {
            Customer c = cr.getCustomerDetails(id);

            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            arrBuilder.add(c.toJson());
            JsonArray result = arrBuilder.build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());

        } catch (Exception e) {

            String errMsg = "User or user content not found for id " + id;

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errMsg);
        }
    }

    @GetMapping(path = "{id}/orders")
    public ResponseEntity<String> getCustomerOrders(
            @PathVariable String id) {

        try {
            List<Order> orders = cr.getCustomerOrders(id);

            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Order o : orders)
                arrBuilder.add(o.toJson());

            JsonArray result = arrBuilder.build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());

        } catch (Exception e) {

            String errMsg = "User or user content not found for id " + id;

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errMsg);
        }
    }

}
