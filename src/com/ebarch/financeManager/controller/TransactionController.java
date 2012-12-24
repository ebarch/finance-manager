package com.ebarch.financeManager.controller;

import com.ebarch.financeManager.dao.TransactionDAO;
import com.ebarch.financeManager.domain.Transaction;
import com.ebarch.financeManager.util.DatabaseManager;
import com.ebarch.financeManager.util.TransactionTypeEnum;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class TransactionController {

    public int addTransaction(Transaction transaction, String categoryName) {
        int updateResult = 0;
        
        try {
            Connection dbConnection = DatabaseManager.openDatabase();
            TransactionDAO transactionDAO = new TransactionDAO(dbConnection);
            updateResult = transactionDAO.insertTransaction(transaction, categoryName);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResult;
    }
    
    public List<Transaction> getAllTransactionsSince(Calendar date) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        
        try {          
            Connection dbConnection = DatabaseManager.openDatabase();
            TransactionDAO transactionDAO = new TransactionDAO(dbConnection);
            transactions = transactionDAO.selectAllTransactionsSince(date);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactions;
    }
    
    public List<Transaction> getAllTransactionsByMonthYearCategory(int month, int year, int categoryId) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        
        try {          
            Connection dbConnection = DatabaseManager.openDatabase();
            TransactionDAO transactionDAO = new TransactionDAO(dbConnection);
            transactions = transactionDAO.selectAllTransactionsByMonthYearCategory(month, year, categoryId);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactions;
    }
    
    public double getTransactionsTotalByMonthYearCategory(int month, int year, int categoryId) {
        List<Transaction> transactions = getAllTransactionsByMonthYearCategory(month, year, categoryId);
        double totalAmount = 0.0;
        
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getType() == TransactionTypeEnum.CREDIT) {
                totalAmount += transaction.getAmount();
            } else {
                totalAmount -= transaction.getAmount();
            }
        }
        return totalAmount;
    }
}
