package com.cts.customerloyalty;

import com.cts.customerloyalty.db.DBConnection;
import com.cts.customerloyalty.model.Customer;
import com.cts.customerloyalty.model.Purchase;
import com.cts.customerloyalty.model.Reward;
import com.cts.customerloyalty.service.CustomerService;
import com.cts.customerloyalty.service.PurchaseService;
import com.cts.customerloyalty.service.RewardService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static CustomerService customerService;
    private static RewardService rewardService;
    private static PurchaseService purchaseService;

    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            customerService = new CustomerService(connection);
            rewardService = new RewardService(connection);
            purchaseService = new PurchaseService(connection);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- Customer Loyalty Program ---");
                System.out.println("1. Manage Customers");
                System.out.println("2. Manage Rewards");
                System.out.println("3. Manage Purchases");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manageCustomers(scanner);
                        break;
                    case 2:
                        manageRewards(scanner);
                        break;
                    case 3:
                        managePurchases(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void manageCustomers(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. View All Customers");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new customer
                    Customer newCustomer = new Customer();
                    System.out.print("Enter name: ");
                    newCustomer.setName(scanner.next());
                    System.out.print("Enter email: ");
                    newCustomer.setEmail(scanner.next());
                    System.out.print("Enter phone: ");
                    newCustomer.setPhone(scanner.next());
                    System.out.print("Enter total points: ");
                    newCustomer.setTotalPoints(scanner.nextInt());
                    customerService.addCustomer(newCustomer);
                    System.out.println("Customer added successfully.");
                    break;

                case 2:
                    // View a customer
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    Customer customer = customerService.getCustomerById(customerId);
                    if (customer != null) {
                        System.out.println("Customer Details: " + customer.getName() + ", " + customer.getEmail() + ", " + customer.getPhone() + ", Points: " + customer.getTotalPoints());
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 3:
                    // Update a customer
                    System.out.print("Enter customer ID to update: ");
                    customerId = scanner.nextInt();
                    customer = customerService.getCustomerById(customerId);
                    if (customer != null) {
                        System.out.print("Enter new name: ");
                        customer.setName(scanner.next());
                        System.out.print("Enter new email: ");
                        customer.setEmail(scanner.next());
                        System.out.print("Enter new phone: ");
                        customer.setPhone(scanner.next());
                        System.out.print("Enter new total points: ");
                        customer.setTotalPoints(scanner.nextInt());
                        customerService.updateCustomer(customer);
                        System.out.println("Customer updated successfully.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    // Delete a customer
                    System.out.print("Enter customer ID to delete: ");
                    customerId = scanner.nextInt();
                    customerService.deleteCustomer(customerId);
                    System.out.println("Customer deleted successfully.");
                    break;

                case 5:
                    // View all customers
                    List<Customer> customers = customerService.getAllCustomers();
                    for (Customer c : customers) {
                        System.out.println("ID: " + c.getCustomerId() + ", Name: " + c.getName() + ", Email: " + c.getEmail() + ", Phone: " + c.getPhone() + ", Points: " + c.getTotalPoints());
                    }
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    private static void manageRewards(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Reward Management ---");
            System.out.println("1. Add Reward");
            System.out.println("2. View Reward");
            System.out.println("3. Update Reward");
            System.out.println("4. Delete Reward");
            System.out.println("5. View All Rewards");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new reward
                    Reward newReward = new Reward();
                    System.out.print("Enter reward name: ");
                    newReward.setName(scanner.next());
                    System.out.print("Enter points required: ");
                    newReward.setPointsRequired(scanner.nextInt());
                    System.out.print("Enter description: ");
                    newReward.setDescription(scanner.next());
                    rewardService.addReward(newReward);
                    System.out.println("Reward added successfully.");
                    break;

                case 2:
                    // View a reward
                    System.out.print("Enter reward ID: ");
                    int rewardId = scanner.nextInt();
                    Reward reward = rewardService.getRewardById(rewardId);
                    if (reward != null) {
                        System.out.println("Reward Details: " + reward.getName() + ", Points: " + reward.getPointsRequired() + ", Description: " + reward.getDescription());
                    } else {
                        System.out.println("Reward not found.");
                    }
                    break;

                case 3:
                    // Update a reward
                    System.out.print("Enter reward ID to update: ");
                    rewardId = scanner.nextInt();
                    reward = rewardService.getRewardById(rewardId);
                    if (reward != null) {
                        System.out.print("Enter new name: ");
                        reward.setName(scanner.next());
                        System.out.print("Enter new points required: ");
                        reward.setPointsRequired(scanner.nextInt());
                        System.out.print("Enter new description: ");
                        reward.setDescription(scanner.next());
                        rewardService.updateReward(reward);
                        System.out.println("Reward updated successfully.");
                    } else {
                        System.out.println("Reward not found.");
                    }
                    break;

                case 4:
                    // Delete a reward
                    System.out.print("Enter reward ID to delete: ");
                    rewardId = scanner.nextInt();
                    rewardService.deleteReward(rewardId);
                    System.out.println("Reward deleted successfully.");
                    break;

                case 5:
                    // View all rewards
                    List<Reward> rewards = rewardService.getAllRewards();
                    for (Reward r : rewards) {
                        System.out.println("ID: " + r.getRewardId() + ", Name: " + r.getName() + ", Points: " + r.getPointsRequired() + ", Description: " + r.getDescription());
                    }
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    private static void managePurchases(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Purchase Management ---");
            System.out.println("1. Add Purchase");
            System.out.println("2. View Purchase");
            System.out.println("3. Update Purchase");
            System.out.println("4. Delete Purchase");
            System.out.println("5. View All Purchases");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new purchase
                    Purchase newPurchase = new Purchase();
                    System.out.print("Enter customer ID: ");
                    newPurchase.setCustomerId(scanner.nextInt());
                    System.out.print("Enter reward ID: ");
                    newPurchase.setRewardId(scanner.nextInt());
                    System.out.print("Enter purchase date (YYYY-MM-DD): ");
                    newPurchase.setPurchaseDate(scanner.next());
                    System.out.print("Enter points earned: ");
                    newPurchase.setPointsEarned(scanner.nextInt());
                    purchaseService.addPurchase(newPurchase);
                    System.out.println("Purchase added successfully.");
                    break;

                case 2:
                    // View a purchase
                    System.out.print("Enter purchase ID: ");
                    int purchaseId = scanner.nextInt();
                    Purchase purchase = purchaseService.getPurchaseById(purchaseId);
                    if (purchase != null) {
                        System.out.println("Purchase Details: Customer ID: " + purchase.getCustomerId() + ", Reward ID: " + purchase.getRewardId() + ", Date: " + purchase.getPurchaseDate() + ", Points: " + purchase.getPointsEarned());
                    } else {
                        System.out.println("Purchase not found.");
                    }
                    break;

                case 3:
                    // Update a purchase
                    System.out.print("Enter purchase ID to update: ");
                    purchaseId = scanner.nextInt();
                    purchase = purchaseService.getPurchaseById(purchaseId);
                    if (purchase != null) {
                        System.out.print("Enter new customer ID: ");
                        purchase.setCustomerId(scanner.nextInt());
                        System.out.print("Enter new reward ID: ");
                        purchase.setRewardId(scanner.nextInt());
                        System.out.print("Enter new purchase date (YYYY-MM-DD): ");
                        purchase.setPurchaseDate(scanner.next());
                        System.out.print("Enter new points earned: ");
                        purchase.setPointsEarned(scanner.nextInt());
                        purchaseService.updatePurchase(purchase);
                        System.out.println("Purchase updated successfully.");
                    } else {
                        System.out.println("Purchase not found.");
                    }
                    break;

                case 4:
                    // Delete a purchase
                    System.out.print("Enter purchase ID to delete: ");
                    purchaseId = scanner.nextInt();
                    purchaseService.deletePurchase(purchaseId);
                    System.out.println("Purchase deleted successfully.");
                    break;

                case 5:
                    // View all purchases
                    List<Purchase> purchases = purchaseService.getAllPurchases();
                    for (Purchase p : purchases) {
                        System.out.println("ID: " + p.getPurchaseId() + ", Customer ID: " + p.getCustomerId() + ", Reward ID: " + p.getRewardId() + ", Date: " + p.getPurchaseDate() + ", Points: " + p.getPointsEarned());
                    }
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}

