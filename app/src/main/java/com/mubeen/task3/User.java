package com.mubeen.task3;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String currentHeight;
    private String currentWeight;
    private String weightGoal;
    private String calorieIntakeGoal;
    private HashMap<String, String> deltaWeight;
    private String system;

    public User() { }


    public String getID() { return id; }

    public void setID(String id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(String currentHeight) {
        this.currentHeight = currentHeight;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(String weightGoal) {
        this.weightGoal = weightGoal;
    }

    public String getCalorieIntakeGoal() {
        return calorieIntakeGoal;
    }

    public void setCalorieIntakeGoal(String calorieIntakeGoal) { this.calorieIntakeGoal = calorieIntakeGoal; }

    public String getUserId() {
        return id;
    }

    public void setUserId(String id) {
        this.id = id;
    }

    public HashMap<String, String> getDeltaWeight() {
        return deltaWeight;
    }

    public void setDeltaWeight(HashMap<String, String> deltaWeight) {
        this.deltaWeight = deltaWeight;
    }

    public String getSystem() { return system; }

    public void setSystem(String system) { this.system = system; }
}
