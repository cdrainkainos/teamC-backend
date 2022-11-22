package com.kainos.ea.contructors.Employee;

public final class HR extends Employee implements Salary{

    private boolean isManager;

    public HR(int number, String fName, String lName, String NIN, String bankAccount, String address, float startSalary, boolean isManager) {
        super(number, fName, lName, NIN, bankAccount, address, startSalary);

        this.isManager = isManager;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    @Override
    public float getMonthlySalary() {
        return 0;
    }
}
