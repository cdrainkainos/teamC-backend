package com.kainos.ea.dao;

import com.kainos.ea.model.employeerequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.kainos.ea.database.DatabaseConnection.closeConnection;
import static com.kainos.ea.database.DatabaseConnection.getConnection;

public class EmployeeDao {

    public int addEmployee(employeerequest employee) {
        int id = 0;
        try {
            Connection c = getConnection();

            String employeeS = "INSERT INTO Employee (fname, lname, bank_account, salary, NIN, address) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt1 = c.prepareStatement(employeeS, Statement.RETURN_GENERATED_KEYS);
            preparedStmt1.setString(1, employee.getfName());
            preparedStmt1.setString(2, employee.getlName());
            preparedStmt1.setString(3, employee.getBankAccount());
            preparedStmt1.setFloat(4, employee.getStartSalary());
            preparedStmt1.setString(5, employee.getNIN());
            preparedStmt1.setString(6, employee.getAddress());

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return id;
    }

    public ArrayList getEmployees() {

        String query;
        ArrayList<employeerequest> employees = new ArrayList<>();

        query = "SELECT id, CONCAT(fname, \" \", lname) AS name\n" +
                "FROM Employee;";
        try {
            Connection conn = getConnection();

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                employeerequest emp = new employeerequest(
                        rs.getInt("id"),
                        rs.getString("fname"),
                        rs.getString("lname"));

                employees.add(emp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return employees;
    }





}
