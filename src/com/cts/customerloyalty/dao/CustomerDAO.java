package com.cts.customerloyalty.dao;


import com.cts.customerloyalty.model.Customer;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers();
}

