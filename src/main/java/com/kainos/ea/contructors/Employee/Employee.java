package com.kainos.ea.contructors.Employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    protected int id; // employee number
    protected String fName;
    protected String lName;
    protected String NIN;
    protected String bankAccount;
    protected String address;
    protected float startSalary;

    @JsonCreator
    public Employee(@JsonProperty("number") int number,
                    @JsonProperty("fName") String fName,
                    @JsonProperty("lName") String lName,
                    @JsonProperty("NIN") String NIN,
                    @JsonProperty("bankAccount") String bankAccount,
                    @JsonProperty("address") String address,
                    @JsonProperty("startSalary") float startSalary) {

        this.id = number;
        this.fName = fName;
        this.lName = lName;
        this.NIN = NIN;
        this.bankAccount = bankAccount;
        this.address = address;
        this.startSalary = startSalary;
    }

    @JsonCreator
    public Employee(@JsonProperty("number") int number,
                    @JsonProperty("fName") String fName,
                    @JsonProperty("lName") String lName) {

        this.id = number;
        this.fName = fName;
        this.lName = lName;
    }

    public int getNumber() {
        return this.id;
    }

    public void setNumber(int number) {
        this.id = number;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getNIN() {
        return NIN;
    }

    public void setNIN(String NIN) {
        this.NIN = NIN;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getStartSalary() {
        return startSalary;
    }

    public void setStartSalary(float startSalary) {
        this.startSalary = startSalary;
    }

    public String toString(){
        String messageAll = "id:" + getNumber() + " " + getfName() + " "+ getlName()+ " "+ getNIN()+ " "+ getBankAccount() + " "+ getAddress();
        return messageAll;
    }
}
