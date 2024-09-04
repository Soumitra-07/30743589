package com.cts.customerloyalty.service;


import com.cts.customerloyalty.dao.CustomerDAO;
import com.cts.customerloyalty.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerDAO {

    private Connection connection;

    public CustomerService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (name, email, phone, total_points) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getEmail());
            pst.setString(3, customer.getPhone());
            pst.setInt(4, customer.getTotalPoints());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";
        Customer customer = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setTotalPoints(rs.getInt("total_points"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET name = ?, email = ?, phone = ?, total_points = ? WHERE customer_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getEmail());
            pst.setString(3, customer.getPhone());
            pst.setInt(4, customer.getTotalPoints());
            pst.setInt(5, customer.getCustomerId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setTotalPoints(rs.getInt("total_points"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}

