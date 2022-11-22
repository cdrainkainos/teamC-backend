package com.kainos.ea.controller;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.dao.ProjectDao;
import com.kainos.ea.dao.User;
import com.kainos.ea.dao.salesEmployeeDao;
import com.kainos.ea.model.*;
import com.kainos.ea.contructors.Employee.Employee;
import com.kainos.ea.contructors.project.Project;
import com.kainos.ea.contructors.project.ProjectEmployees;
import com.kainos.ea.service.employeeservice;
import com.kainos.ea.service.projectservice;
import com.kainos.ea.service.salesemployeeservice;
import com.kainos.ea.service.userservice;
import com.kainos.ea.validator.isValidEmployee;
import com.kainos.ea.validator.isValidSalesEmployee;
import com.kainos.ea.validator.isValidUser;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import static com.kainos.ea.database.DatabaseConnection.getConnection;
import static com.kainos.ea.database.DatabaseConnection.closeConnection;
@Api("Engineering Academy Dropwizard API")

@Path("/api")
public class WebService {

    private static com.kainos.ea.validator.isValidSalesEmployee validSales;
    private static com.kainos.ea.validator.isValidEmployee validEmp;
    private static com.kainos.ea.service.userservice users;
    private static com.kainos.ea.validator.isValidUser validUser;
    private static com.kainos.ea.service.salesemployeeservice salesservice;

    private static com.kainos.ea.service.projectservice projectservice;
    private static com.kainos.ea.service.employeeservice employeeservice;
    //private static com.kainos.ea.dao.salesEmployeeDao salesEmpDao;
    public WebService() {
        validEmp = new isValidEmployee();
        validSales = new isValidSalesEmployee();
        validUser = new isValidUser();
        users = new userservice( new User() );
        salesservice = new salesemployeeservice( new salesEmployeeDao());
        employeeservice = new employeeservice( new EmployeeDao());
        projectservice = new projectservice(new ProjectDao());
    }

    @POST
    @Path("/AddSalesEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddSales(salesemployeerequest message) {

        System.out.println(message);
        if(validSales.isSalesEmployeeValid(message)) {
            try {
                int id = salesservice.insertSalesEmployee(message);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            }
            catch(Exception e){
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            }
        }else{
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }

    }

    @POST
    @Path("/AddUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddUser(UserRequest message) {


        if(validUser.isUserValid(message)) {
            try {
                int id = users.InsertUser(message);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            }
            catch(Exception e){
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            }
        }else{
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }

    }

    /*
     * This post endpoint creates an instance of an employee in the Employee table. This employee is not
     * assigned to any particular business role.
     */
    @POST
    @Path("/addEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee( employeerequest employee) {

            if(validEmp.isEmployeeValid(employee)) {
                try {
                    int id = employeeservice.AddEmployee(employee);
                    return Response.status(HttpStatus.CREATED_201).entity(id).build();
                }
                catch(Exception e){
                    System.out.println(e);
                    return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
                }
            }else{
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }

        }


    /*
     * This creates a new project instance in the db with a configured name
     */
    @POST
    @Path("/createProject")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(projectrequest project) {

            try {
                int id = projectservice.AddProject(project);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            }
            catch(Exception e){
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            }
        }



    @GET
    @Path("/departmentreport")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeDepartment() {

        try {
            return Response.ok(salesservice.getEmployeeDepartment()).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }

    }

    @GET
    @Path("/grosspayreport")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeGrossPay() {
        try {
            return Response.ok(salesservice.grosspay()).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }


    @GET
    @Path("/getsalesemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployee () {
        try {
                return Response.ok(salesservice.getSalesEmployees()).build();
            } catch (Exception e) {
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            }
        }

    @POST
    @Path("/isUserValid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isUserValid (UserRequest userRequest) {
        int id = users.getUser(userRequest);

        try {
        if(id > 0){
            return Response.status(HttpStatus.CREATED_201).entity(id).build();
        }else{
            return Response.status(0).build();
        }
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }


        @GET
        @Path("/highSalesTotal")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getHighestSalesTotal () {
            try {
                return Response.ok(salesservice.getHighestSalesTotal()).build();
            } catch (Exception e) {
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            }
        }

        /*
         * This class simply assigns an existing employee to an existing project.
         */
        @POST
        @Path("/assignEmployeeToProject")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response assignEmployeeToProject (projectemployees projectEmployees){

                try {
                    int id =projectservice.AddEmployeeToProject(projectEmployees);
                    return Response.status(HttpStatus.CREATED_201).entity(id).build();
                }
                catch(Exception e){
                    System.out.println(e);
                    return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
                }
            }

    @GET
    @Path("/assignedemployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignedemployees () {
        try {
            return Response.ok(projectservice.assignedemployees()).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/getemployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees () {
        try {
            return Response.ok(employeeservice.getEmployees()).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

}
