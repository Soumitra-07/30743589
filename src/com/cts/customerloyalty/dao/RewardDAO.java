package com.cts.customerloyalty.dao;
import com.cts.customerloyalty.model.Reward;
import java.util.List;
public interface RewardDAO {
    void addReward(Reward reward);
    Reward getRewardById(int rewardId);
    void updateReward(Reward reward);
    void deleteReward(int rewardId);
    List<Reward> getAllRewards();
}

