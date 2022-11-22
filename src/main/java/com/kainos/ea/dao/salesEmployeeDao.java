package com.kainos.ea.dao;

import com.kainos.ea.model.salesemployeerequest;
import com.kainos.ea.contructors.Employee.SalesEmployee;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kainos.ea.database.DatabaseConnection.closeConnection;
import static com.kainos.ea.database.DatabaseConnection.getConnection;

public class salesEmployeeDao {

    public int insertSalesEmployee(salesemployeerequest message) {
        int id = 0;
        try {
            Connection c = getConnection();

            String employee = "INSERT INTO Employee (fname, lname, bank_account, salary, NIN, address) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt1 = c.prepareStatement(employee, Statement.RETURN_GENERATED_KEYS);
            preparedStmt1.setString(1, message.getfName());
            preparedStmt1.setString(2, message.getlName());
            preparedStmt1.setString(3, message.getBankAccount());
            preparedStmt1.setFloat(4, message.getStartSalary());
            preparedStmt1.setString(5, message.getNIN());
            preparedStmt1.setString(6, message.getAddress());

            preparedStmt1.execute();


            ResultSet rs = preparedStmt1.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            System.out.println(id);

            String query = " insert into Sales_Employees (id, comission_rate, total_sales_value, manager)"
                    + " values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setFloat(2, message.getComission());
            preparedStmt.setFloat(3, message.getTotalSales());
            preparedStmt.setBoolean(4, message.isManager());

            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return id;
    }

    public List<SalesEmployee> getEmployees() {
        String s = " SELECT Employee.id, Employee.fname, Employee.lname, Employee.bank_account, Employee.salary, Employee.NIN, Employee.address, Sales_Employees.comission_rate, Sales_Employees.total_sales_value, Sales_Employees.manager FROM " +
                "Sales_Employees JOIN Employee ON Employee.id = Sales_Employees.id";


        List<SalesEmployee> salesemp = new ArrayList<>();

        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(s);

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();
            while (rs.next()) {
                SalesEmployee employee = new SalesEmployee(
                        rs.getInt("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("NIN"),
                        rs.getString("bank_account"),
                        rs.getString("address"),
                        rs.getFloat("salary"),
                        rs.getBoolean("manager"),
                        rs.getFloat("comission_rate"),
                        rs.getFloat("total_sales_value")

                );
                salesemp.add(employee);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return salesemp;

    }

    public String getEmployeeGrossPay() {
        String s = "SELECT e.*, IFNULL(s.comission_rate, 0) com, IFNULL(s.total_sales_value, 0) sales,TRUNCATE(IFNULL(((s.comission_rate / 100) * s.total_sales_value + e.salary) / 12," +
                "e.salary / 12), 2) this_month_gross FROM Employee e LEFT JOIN Sales_Employees s ON e.id = s.id";
        JSONObject obj = null;
        JSONArray json = new JSONArray();
        try {
            Connection c = getConnection();

            PreparedStatement preparedStmt1 = c.prepareStatement(s);

            ResultSet rs = preparedStmt1.executeQuery();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("id", rs.getObject("id"));
                obj.put("Forename", rs.getObject("fname"));
                obj.put("Surname", rs.getObject("lname"));
                obj.put("This month gross £", rs.getObject("this_month_gross"));
                json.put(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Bad practice alert!
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return json.toString();
    }

    public String getEmployeeDepartment() {


                String s = "with finance as (select f.id, \"Finance\" as Department from Finance_Employees f),  sales as (select s.id, \"Sales\" as Department from Sales_Employees s),\n" +
                        "hr as (select h.id, \"HR\" as Department from HR_Employees h), alldep as ( select * from finance union select * from sales union select * from hr) \n" +
                        "select e.*, \n" +
                        "IFNULL(alldep.Department, \"Unassigned\") as Department\n" +
                        "from Employee e left join alldep on e.id = alldep.id";

        JSONObject obj = null;
        JSONArray json = new JSONArray();
        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(s);

            ResultSet rs = preparedStmt1.executeQuery();

            while (rs.next()) {
                obj = new JSONObject();
                obj.put("id", rs.getObject("id"));
                obj.put("Forename", rs.getObject("fname"));
                obj.put("Surname", rs.getObject("lname"));
                obj.put("Department", rs.getObject("Department"));
                json.put(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Bad practice alert!
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return json.toString();
    }

    public String getHighestSalesTotal () {
        StringBuilder sb = new StringBuilder();

        String s = "SELECT CONCAT(Employee.fname, Employee.lname) AS employee_name, Sales_Employees.total_sales_value FROM Sales_Employees" +
                " JOIN Employee ON Employee.id = Sales_Employees.id WHERE total_sales_value = (SELECT MAX(total_sales_value) FROM Sales_Employees) LIMIT 1;";
        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(s);

            ResultSet rs = preparedStmt1.executeQuery();

            while (rs.next()) {
                sb.append(rs.getString("employee_name")).append(" has the highest total sales with £").append(rs.getString("total_sales_value"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            closeConnection();
        }

        return sb.toString();
    }

}
