package com.kainos.ea.validator;

import com.kainos.ea.model.UserRequest;

public class isValidUser {
    public boolean isUserValid(UserRequest req){
        if(req.getPwd().length() < 1 || req.getUsername().length() <1){
            return false;
        }


        return true;
    }
}