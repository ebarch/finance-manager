package com.ebarch.financeManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ed
 */
public class TransactionTypeDAO {
    
    private final Connection dbConnection;
    
    public TransactionTypeDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public int selectIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("SELECT transaction_type_id FROM transaction_types WHERE name = ?");
        preparedStatement.setString(1, name);
        ResultSet queryResult = preparedStatement.executeQuery();
        
        int transactionId = -1;
        if (queryResult.next()) {
            transactionId = queryResult.getInt("transaction_type_id");
        }
        return transactionId;
    }

    String selectNameById(int transactionTypeId) throws SQLException {
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("SELECT name FROM transaction_types WHERE transaction_type_id= ?");
        preparedStatement.setInt(1, transactionTypeId);
        ResultSet queryResult = preparedStatement.executeQuery();
        
        String name = null;
        if (queryResult.next()) {
            name = queryResult.getString("name");
        }
        return name;
    }
}
