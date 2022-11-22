package com.kainos.ea;


import com.kainos.ea.controller.WebService;
import com.kainos.ea.contructors.Employee.Employee;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kainos.ea.database.DatabaseConnection.getConnection;


public class trueApplication extends Application<trueConfiguration> {

    public static List<Employee> getEmployees() throws SQLException {
        Connection c = getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT * "
                        + "FROM Employee;");

        List<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            Employee employee = new Employee(
                    rs.getInt("id"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("NIN"),
                    rs.getString("bank_account"),
                    rs.getString("address"),
                    rs.getFloat("salary")
                    //rs.get("Salary")
            );
            employees.add(employee);
        }
        return employees;
    }

    public static void main(final String[] args) throws Exception {
        new trueApplication().run(args);

    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<trueConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new SwaggerBundle<trueConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(trueConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final trueConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new WebService());
        // TODO: implement application
    }

}
