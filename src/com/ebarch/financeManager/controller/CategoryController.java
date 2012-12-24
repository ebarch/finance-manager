package com.ebarch.financeManager.controller;

import com.ebarch.financeManager.dao.CategoryDAO;
import com.ebarch.financeManager.domain.Category;
import com.ebarch.financeManager.util.DatabaseManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class CategoryController {
    
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            Connection dbConnection = DatabaseManager.openDatabase();
            CategoryDAO categoryDAO = new CategoryDAO(dbConnection);
            categories = categoryDAO.selectAllCategories();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }  
    
    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<String>();
        try {
            Connection dbConnection = DatabaseManager.openDatabase();
            CategoryDAO categoryDAO = new CategoryDAO(dbConnection);
            categoryNames = categoryDAO.selectAllCategoryNames();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryNames;
    }    
}
