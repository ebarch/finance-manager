package com.ebarch.financeManager.util;

import java.sql.*;

/**
 *
 * @author Ed
 */
public class DatabaseManager {
    private static Connection databaseConnection; 
    private static PreparedStatement sqlStatement;
    
    public static Connection openDatabase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();

        databaseConnection = DriverManager.getConnection("jdbc:derby:C:/devl/workspaces/finance-manager/derby-database;create=true");

        return databaseConnection;
    }

    public static void closeDatabase() throws SQLException {        
        if (databaseConnection != null) {
            databaseConnection = DriverManager.getConnection("jdbc:derby:;shutdown=true");
            //sqlStatement = databaseConnect.prepareStatement("SHUTDOWN COMPACT");
            //sqlStatement.executeUpdate();
        }
        if (sqlStatement != null) {
            sqlStatement.close();
        }
    }
    
    public static void createDatabase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DatabaseMetaData databaseMetaData = databaseConnection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, "APP", "transaction_types", null);
        System.out.println("meta data: " + resultSet);
        
        while (resultSet.next()) {
            System.out.println("result set has stuff...");
            System.out.println(resultSet.getMetaData().getTableName(0));            
        }
        
        if(!resultSet.next()) {
            sqlStatement = databaseConnection.prepareStatement("CREATE TABLE transaction_types (transaction_type_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, name VARCHAR(255) UNIQUE NOT NULL, value SMALLINT UNIQUE NOT NULL)");
            sqlStatement.executeUpdate();
            sqlStatement = databaseConnection.prepareStatement("CREATE TABLE categories (category_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, name VARCHAR(255) UNIQUE NOT NULL, description VARCHAR(255))");
            sqlStatement.executeUpdate();
            sqlStatement = databaseConnection.prepareStatement("CREATE TABLE transactions (transaction_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, amount DECIMAL(10,2), description VARCHAR(255), date DATE, transaction_type_id BIGINT NOT NULL REFERENCES transaction_types(transaction_type_id), category_id BIGINT NOT NULL REFERENCES categories(category_id))");
            sqlStatement.executeUpdate();
            sqlStatement = databaseConnection.prepareStatement("CREATE TABLE deleted_transactions (deleted_transaction_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, amount DECIMAL(10,2), description VARCHAR(255), transaction_date DATE, date_deleted DATE, transaction_type_id BIGINT NOT NULL REFERENCES transaction_types(transaction_type_id), category_id BIGINT NOT NULL REFERENCES categories(category_id))");
            sqlStatement.executeUpdate();
        }
        //sqlStatement = databaseConnect.prepareStatement("SET WRITE_DELAY 0");
        //sqlStatement.executeUpdate();
    }
}