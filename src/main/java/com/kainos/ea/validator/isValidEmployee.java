package com.kainos.ea.validator;

import com.kainos.ea.model.employeerequest;

public class isValidEmployee {

    public boolean isEmployeeValid(employeerequest req){
        if(req.getfName().length() < 1 || req.getlName().length() <1){
            return false;
        }

        if(req.getAddress().length() < 1 || req.getAddress().length() > 200){
            return false;
        }
        if(req.getStartSalary() < 1000 || req.getStartSalary() > 10000000){
            return false;
        }

        return true;
    }
}
