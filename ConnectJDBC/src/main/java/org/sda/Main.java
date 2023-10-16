package org.sda;

import databaseConnection.SqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = SqlConnection.getSqlConnection();
    }
}