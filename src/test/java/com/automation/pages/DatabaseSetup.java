package com.automation.pages;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSetup {
    private Connection connection;
    private Scenario scenario;

    @BeforeStep
    public void startup(Scenario scenario) throws SQLException {
        this.scenario = scenario;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/training", "vusi", "password"
        );

        scenario.log("Connected to the database.");
    }

    public Connection getConnection() {
        return connection;
    }
}

