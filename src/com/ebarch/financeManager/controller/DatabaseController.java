package com.ebarch.financeManager.controller;

import com.ebarch.financeManager.util.DatabaseManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class DatabaseController {
    
    public static void createDatabase() {
        try {
            DatabaseManager.openDatabase();
            DatabaseManager.createDatabase();
            //DatabaseManager.closeDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
