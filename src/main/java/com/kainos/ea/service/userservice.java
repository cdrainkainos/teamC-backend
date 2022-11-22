package com.kainos.ea.service;

import com.kainos.ea.dao.User;
import com.kainos.ea.model.UserRequest;


public class userservice {
    public com.kainos.ea.dao.User dao;


    public userservice(User dao){
        this.dao =dao;
    }
    public int InsertUser (UserRequest req){
        return dao.AddUser(req);
    }

    public int getUser(UserRequest req)   {
        return dao.LoginUser(req);
    }


}
