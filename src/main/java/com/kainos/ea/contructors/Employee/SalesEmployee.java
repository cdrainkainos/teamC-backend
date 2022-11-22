package com.kainos.ea.contructors.Employee;

public final class SalesEmployee extends Employee implements Salary{

    private float comission;
    private float totalSales;

    private boolean isManager;

    private static float STANDARD_COMMISION= 9.99f;

    public SalesEmployee(int number, String fName, String lName, String NIN, String bankAccount,
                         String address, float startSalary, boolean isManager) {
        super(number, fName, lName, NIN, bankAccount, address, startSalary);
        this.comission = STANDARD_COMMISION;
        this.totalSales = 0;
        this.isManager = isManager;
    }

    public SalesEmployee(int number, String fName, String lName, String NIN, String bankAccount,
                         String address, float startSalary, boolean isManager, float comission, float totalSales) {

        super(number, fName, lName, NIN, bankAccount, address, startSalary);
        this.comission = comission;
        this.totalSales = totalSales;
        this.isManager = isManager;
    }

    public SalesEmployee(int number, String fName, String lName, String NIN, String bankAccount, String address, float startSalary, float comission) {
        super(number, fName, lName, NIN, bankAccount, address, startSalary);
        this.comission = comission;
    }

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


    @Override
    public float getMonthlySalary() {
        return 0;
    }
}
