package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class projectemployees {

    private int projectID;
    private int employeeID;

    private boolean isManager;

    @JsonCreator
    public projectemployees(@JsonProperty("projectID") int projectID, @JsonProperty("employeeID") int employeeID ,
                            @JsonProperty("isManager") boolean isManager) {
        this.projectID = projectID;
        this.employeeID = employeeID;
        this.isManager = isManager;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
