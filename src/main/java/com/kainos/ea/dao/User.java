package com.kainos.ea.dao;


import com.kainos.ea.model.UserRequest;

import java.sql.*;

import static com.kainos.ea.database.DatabaseConnection.closeConnection;
import static com.kainos.ea.database.DatabaseConnection.getConnection;

public class User {

    public int AddUser(UserRequest message){
        int id = 0;
        System.out.println(message);
        try {
            Connection c = getConnection();

            String user = "INSERT INTO User (username, pwd) VALUES (?,?)";
            PreparedStatement preparedStmt1 = c.prepareStatement(user, Statement.RETURN_GENERATED_KEYS);
            preparedStmt1.setString(1, message.getUsername());
            preparedStmt1.setString(2, message.getPwd());


            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            System.out.println(id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }
        return id;
    }

    public int LoginUser(UserRequest user){
        int id =0;
        try {
            Connection c = getConnection();

            String s = "SELECT id "
                    + "FROM User "
                    + "WHERE username = ?" +
                    "AND pwd =?";

            PreparedStatement preparedStmt1 = c.prepareStatement(s);
            preparedStmt1.setString(1, user.getUsername());
            preparedStmt1.setString(2, user.getPwd());

            ResultSet rs = preparedStmt1.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }

        }catch(Exception e){

        }finally {
            closeConnection();
        }

        return id;
    }



}

