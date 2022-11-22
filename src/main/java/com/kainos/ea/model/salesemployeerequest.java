package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class salesemployeerequest {

        protected int id; // employee number
        protected String fName;
        protected String lName;
        protected String NIN;
        protected String bankAccount;
        protected String address;
        protected float startSalary;

        private float comission;

        public float getComission() {
            return comission;
        }

        public void setComission(float comission) {
            this.comission = comission;
        }

        public float getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(float totalSales) {
            this.totalSales = totalSales;
        }

        public boolean isManager() {
            return isManager;
        }

        public void setManager(boolean manager) {
            isManager = manager;
        }

        private float totalSales;

        private boolean isManager;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        @JsonCreator
        public salesemployeerequest(
                @JsonProperty("id") int id,
                @JsonProperty("fname") String fname,
                @JsonProperty("lName") String lname,
                @JsonProperty("nin") String Nin,
                @JsonProperty("bankAccount") String bankaccount,
                @JsonProperty("address") String address,
                @JsonProperty("salary") float salary,
                @JsonProperty("comission") float commission,
                @JsonProperty("totalSales") float totalSales,
                @JsonProperty("isManager") boolean isManager) {
            this.setId(id);
            this.setfName(fname);
            this.setlName(lname);
            this.setNIN(Nin);
            this.setBankAccount(bankaccount);
            this.setAddress(address);
            this.setStartSalary(salary);
            this.setComission(commission);
            this.setTotalSales(totalSales);
            this.setManager(isManager);

        }


}
