package com.kainos.ea.service;

import com.kainos.ea.dao.salesEmployeeDao;
import com.kainos.ea.model.salesemployeerequest;
import com.kainos.ea.contructors.Employee.SalesEmployee;

import java.util.List;

public class salesemployeeservice {
public com.kainos.ea.dao.salesEmployeeDao salesEmployeeDao;
public salesemployeeservice(salesEmployeeDao sales){
    this.salesEmployeeDao = sales;
}
public int insertSalesEmployee (salesemployeerequest req){
    return salesEmployeeDao.insertSalesEmployee(req);
}

public String grosspay(){return salesEmployeeDao.getEmployeeGrossPay();}

    public String getHighestSalesTotal(){return salesEmployeeDao.getHighestSalesTotal();}
    public String getEmployeeDepartment() {return salesEmployeeDao.getEmployeeDepartment();}
    public List<SalesEmployee> getSalesEmployees()   {
        return salesEmployeeDao.getEmployees();
    }


}
