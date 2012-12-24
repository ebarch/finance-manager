package com.ebarch.financeManager.dao;

import com.ebarch.financeManager.domain.Category;
import com.ebarch.financeManager.domain.Transaction;
import com.ebarch.financeManager.util.TransactionTypeEnum;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Ed
 */
public class TransactionDAO {
    
    private final Connection dbConnection;
    
    public TransactionDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public int insertTransaction(Transaction transaction, String categoryName) throws SQLException {
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("INSERT INTO transactions (amount, description, date, category_id, transaction_type_id) VALUES (?, ?, ?, ?, ?)");
        
        CategoryDAO categoryDAO = new CategoryDAO(this.dbConnection);
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(this.dbConnection);
        
        preparedStatement.setDouble(1, transaction.getAmount());
        preparedStatement.setString(2, transaction.getDescription());
        preparedStatement.setDate(3, new Date(transaction.getDate().getTimeInMillis()));
        preparedStatement.setInt(4, categoryDAO.selectIdByName(categoryName));
        preparedStatement.setInt(5, transactionTypeDAO.selectIdByName(transaction.getType().toString()));
        
        return preparedStatement.executeUpdate();           
    }

    public List<Transaction> selectAllTransactionsSince(Calendar date) throws SQLException {
        PreparedStatement preparedStatement = 
                //this.dbConnection.prepareStatement("SELECT * FROM transactions WHERE date >= ?");
                this.dbConnection.prepareStatement("SELECT * FROM transactions");
        //preparedStatement.setDate(1, new Date(date.getTimeInMillis()));
        ResultSet results = preparedStatement.executeQuery();
        
        List<Transaction> transactions = new ArrayList<Transaction>();
        while (results.next()) {
            Transaction transaction = new Transaction();
            
            transaction.setId(results.getInt("transaction_id"));
            transaction.setAmount(results.getDouble("amount"));
            transaction.setDescription(results.getString("description"));
            
            Calendar transactionDate = Calendar.getInstance();
            transactionDate.setTimeInMillis(results.getDate("date").getTime());
            transaction.setDate(transactionDate);
            
            TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(this.dbConnection);
            String transactionTypeName = transactionTypeDAO.selectNameById(results.getInt("transaction_type_id"));
            transaction.setType(transactionTypeName.equals("DEBIT") ? TransactionTypeEnum.DEBIT : TransactionTypeEnum.CREDIT);
            
            CategoryDAO categoryDAO = new CategoryDAO(this.dbConnection);
            Category category = categoryDAO.selectCategoryById(results.getInt("category_id"));
            transaction.setCategory(category);
            
            transactions.add(transaction);
        }
        return transactions;
    }
    
    public List<Transaction> selectAllTransactionsByMonthYearCategory(int month, int year, int categoryId) throws SQLException {
        // create dates from parameters
        Calendar startDate = Calendar.getInstance();
        startDate.clear();
        startDate.set(Calendar.YEAR, year);
        startDate.set(Calendar.MONTH, month);
        startDate.set(Calendar.DATE, startDate.getActualMinimum(Calendar.DATE));
        
        Calendar endDate = Calendar.getInstance();
        endDate.clear();
        endDate.set(Calendar.YEAR, year);
        endDate.set(Calendar.MONTH, month);
        endDate.set(Calendar.DATE, endDate.getActualMaximum(Calendar.DATE));       
        
        // create  and execute query
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("SELECT * FROM transactions WHERE date BETWEEN ? AND ? AND category_id = ?");
        preparedStatement.setDate(1, new Date(startDate.getTimeInMillis()));
        preparedStatement.setDate(2, new Date(endDate.getTimeInMillis()));
        preparedStatement.setInt(3, categoryId);
        ResultSet results = preparedStatement.executeQuery();
        
        // create transaction objects from query results
        List<Transaction> transactions = new ArrayList<Transaction>();
        while (results.next()) {
            Transaction transaction = new Transaction();
            
            transaction.setId(results.getInt("transaction_id"));
            transaction.setAmount(results.getDouble("amount"));
            transaction.setDescription(results.getString("description"));
            
            Calendar transactionDate = Calendar.getInstance();
            transactionDate.setTimeInMillis(results.getDate("date").getTime());
            transaction.setDate(transactionDate);
            
            TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO(this.dbConnection);
            String transactionTypeName = transactionTypeDAO.selectNameById(results.getInt("transaction_type_id"));
            transaction.setType(transactionTypeName.equals("DEBIT") ? TransactionTypeEnum.DEBIT : TransactionTypeEnum.CREDIT);
            
            CategoryDAO categoryDAO = new CategoryDAO(this.dbConnection);
            Category category = categoryDAO.selectCategoryById(results.getInt("category_id"));
            transaction.setCategory(category);
            
            transactions.add(transaction);
        }
        return transactions;
    }
}
