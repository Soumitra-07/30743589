package com.cts.customerloyalty.model;

public class Reward {
    private int rewardId;
    private String name;
    private int pointsRequired;
    private String description;

    // Getters and Setters
    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

