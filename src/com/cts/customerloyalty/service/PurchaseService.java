package com.cts.customerloyalty.service;

import com.cts.customerloyalty.dao.PurchaseDAO;
import com.cts.customerloyalty.model.Purchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseService implements PurchaseDAO {

    private Connection connection;

    public PurchaseService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addPurchase(Purchase purchase) {
        String sql = "INSERT INTO Purchase (customer_id, reward_id, purchase_date, points_earned) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, purchase.getCustomerId());
            pst.setInt(2, purchase.getRewardId());
            pst.setString(3, purchase.getPurchaseDate());
            pst.setInt(4, purchase.getPointsEarned());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Purchase getPurchaseById(int purchaseId) {
        String sql = "SELECT * FROM Purchase WHERE purchase_id = ?";
        Purchase purchase = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, purchaseId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                purchase = new Purchase();
                purchase.setPurchaseId(rs.getInt("purchase_id"));
                purchase.setCustomerId(rs.getInt("customer_id"));
                purchase.setRewardId(rs.getInt("reward_id"));
                purchase.setPurchaseDate(rs.getString("purchase_date"));
                purchase.setPointsEarned(rs.getInt("points_earned"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        String sql = "UPDATE Purchase SET customer_id = ?, reward_id = ?, purchase_date = ?, points_earned = ? WHERE purchase_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, purchase.getCustomerId());
            pst.setInt(2, purchase.getRewardId());
            pst.setString(3, purchase.getPurchaseDate());
            pst.setInt(4, purchase.getPointsEarned());
            pst.setInt(5, purchase.getPurchaseId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePurchase(int purchaseId) {
        String sql = "DELETE FROM Purchase WHERE purchase_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, purchaseId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT * FROM Purchase";
        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getInt("purchase_id"));
                purchase.setCustomerId(rs.getInt("customer_id"));
                purchase.setRewardId(rs.getInt("reward_id"));
                purchase.setPurchaseDate(rs.getString("purchase_date"));
                purchase.setPointsEarned(rs.getInt("points_earned"));
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }
}
