package com.klef.hibernate_labexam;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID (database auto-increment)
    private int deptId;

    private String name;
    private String location;
    private String hodName;

    // Default constructor
    public Department() {}

    // Constructor with parameters
    public Department(String name, String location, String hodName) {
        this.name = name;
        this.location = location;
        this.hodName = hodName;
    }

    // Getters and Setters
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", name=" + name + ", location=" + location + ", hodName=" + hodName + "]";
    }
}
