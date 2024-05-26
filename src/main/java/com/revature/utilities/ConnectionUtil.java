package com.revature.utilities;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.FileNotFoundException;

public class ConnectionUtil {
    
    public static Connection createConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/planetarium.db");
    }

    public static void main(String[] args) {
        // run this to create database
        try {
            ConnectionUtil.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetTestDatabase() {
        try(Connection connection = ConnectionUtil.createConnection()){
            System.out.println("Connection established......");
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(connection);
            //Creating a reader object
            Reader reader = new BufferedReader(new FileReader("D:\\aRevRepo\\GroupProjectByteBusters\\Automation-Test-Project_ByteBusters\\src\\test\\resources\\sqlscript\\resetdatabase.sql"));
            //Running the script
            sr.runScript(reader);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
