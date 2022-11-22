package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.dao.salesEmployeeDao;
import com.kainos.ea.model.employeerequest;
import com.kainos.ea.model.salesemployeerequest;

import java.sql.ResultSet;
import java.util.ArrayList;


public class employeeservice {
    public com.kainos.ea.dao.EmployeeDao EmployeeDao;

    public employeeservice(EmployeeDao emp){
        this.EmployeeDao = emp;
    }

    public int AddEmployee (employeerequest req){
        return EmployeeDao.addEmployee(req);
    }

    public ArrayList getEmployees () {
        return EmployeeDao.getEmployees();
    }
}
