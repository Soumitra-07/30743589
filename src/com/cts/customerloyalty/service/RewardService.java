package com.cts.customerloyalty.service;

import com.cts.customerloyalty.dao.RewardDAO;
import com.cts.customerloyalty.model.Reward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RewardService implements RewardDAO {

    private Connection connection;

    public RewardService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addReward(Reward reward) {
        String sql = "INSERT INTO Reward (name, points_required, description) VALUES (?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, reward.getName());
            pst.setInt(2, reward.getPointsRequired());
            pst.setString(3, reward.getDescription());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reward getRewardById(int rewardId) {
        String sql = "SELECT * FROM Reward WHERE reward_id = ?";
        Reward reward = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, rewardId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                reward = new Reward();
                reward.setRewardId(rs.getInt("reward_id"));
                reward.setName(rs.getString("name"));
                reward.setPointsRequired(rs.getInt("points_required"));
                reward.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reward;
    }

    @Override
    public void updateReward(Reward reward) {
        String sql = "UPDATE Reward SET name = ?, points_required = ?, description = ? WHERE reward_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, reward.getName());
            pst.setInt(2, reward.getPointsRequired());
            pst.setString(3, reward.getDescription());
            pst.setInt(4, reward.getRewardId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReward(int rewardId) {
        String sql = "DELETE FROM Reward WHERE reward_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, rewardId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reward> getAllRewards() {
        List<Reward> rewards = new ArrayList<>();
        String sql = "SELECT * FROM Reward";
        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Reward reward = new Reward();
                reward.setRewardId(rs.getInt("reward_id"));
                reward.setName(rs.getString("name"));
                reward.setPointsRequired(rs.getInt("points_required"));
                reward.setDescription(rs.getString("description"));
                rewards.add(reward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewards;
    }
}
