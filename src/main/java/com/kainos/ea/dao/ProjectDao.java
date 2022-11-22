package com.kainos.ea.dao;

import com.kainos.ea.contructors.project.Project;
import com.kainos.ea.model.projectemployees;
import com.kainos.ea.model.projectrequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

import static com.kainos.ea.database.DatabaseConnection.closeConnection;
import static com.kainos.ea.database.DatabaseConnection.getConnection;

public class ProjectDao {

    public int assignEmployeeToProject(projectemployees projectEmployees) {
        int id =0;
        try {
            Connection c = getConnection();

            String query = "INSERT INTO Project_Employee (project_id_fk, employee_id_fk, manager) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, projectEmployees.getProjectID());
            preparedStmt.setInt(2, projectEmployees.getEmployeeID());
            preparedStmt.setBoolean(3, projectEmployees.isManager());

            preparedStmt.execute();

            ResultSet rs = preparedStmt.getGeneratedKeys();
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

    public int createProject(projectrequest project) {
        int id =0;
        try {
            Connection c = getConnection();
            String query = "INSERT INTO Projects (project_name) VALUES (?)";
            PreparedStatement preparedStmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, project.getName());

            preparedStmt.execute();

            ResultSet rs = preparedStmt.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }

        return id;
    }

    public String assignedemployees() {

        String s = "with projemp as(\n" +
                "  select\n" +
                "    p.project_id_fk, p.employee_id_fk, e.id, e.fname, e.lname\n" +
                "  from\n" +
                "    Project_Employee p\n" +
                "    LEFT JOIN Employee e on p.employee_id_fk = e.id\n" +
                ")\n" +
                "select\n" +
                "  p.id \"Project_ID\",\n" +
                "  p.project_name \"Project_Name\",\n" +
                "  pe.employee_id_fk \"Employee_ID\",\n" +
                "  pe.fname \"Employee_Forename\",\n" +
                "  pe.lname \"Employee_Surname\"\n" +
                "FROM\n" +
                "  projemp pe\n" +
                "  LEFT JOIN Projects p on p.id = pe.project_id_fk ORDER BY Project_ID";
        JSONObject obj = null;
        JSONArray json = new JSONArray();
        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(s);

            ResultSet rs = preparedStmt1.executeQuery();

            while (rs.next()) {
                obj = new JSONObject();
                obj.put("Project_ID", rs.getObject("Project_ID"));
                obj.put("Project_Name", rs.getObject("Project_Name"));
                obj.put("Employee_ID", rs.getObject("Employee_ID"));
                obj.put("Employee_Forename", rs.getObject("Employee_Forename"));
                obj.put("Employee_Surename", rs.getObject("Employee_Surname"));

                json.put(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Bad practice alert!
        } catch (
                JSONException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return json.toString();

    }
}
