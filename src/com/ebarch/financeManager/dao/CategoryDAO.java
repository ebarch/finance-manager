package com.ebarch.financeManager.dao;

import com.ebarch.financeManager.domain.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ed
 */
public class CategoryDAO {
    
    private final Connection dbConnection;
    
    public CategoryDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public List<Category> selectAllCategories() throws SQLException {
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("SELECT * FROM categories");
        ResultSet queryResult = preparedStatement.executeQuery();
        
        List<Category> categories = new ArrayList<Category>();
        
        while (queryResult.next()) {
            Category category = new Category();
            category.setId(queryResult.getInt("category_id"));
            category.setName(queryResult.getString("name"));
            category.setDescription(queryResult.getString("description"));
            categories.add(category);
        }
        
        return categories;
    }
    
    public List<String> selectAllCategoryNames() throws SQLException {
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("SELECT name FROM categories");
        ResultSet queryResult = preparedStatement.executeQuery();
        
        List<String> categoryNames = new ArrayList<String>();
        
        while (queryResult.next()) {
            categoryNames.add(queryResult.getString("name"));
        }
        
        return categoryNames;
    }
    
    public int selectIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("SELECT category_id FROM categories WHERE name = ?");
        preparedStatement.setString(1, name);
        ResultSet queryResult = preparedStatement.executeQuery();
        
        int categoryId = -1;
        if (queryResult.next()) {
            categoryId = queryResult.getInt("category_id");
        }
        return categoryId;
    }

    public Category selectCategoryById(int id) throws SQLException {
        PreparedStatement preparedStatement = 
                this.dbConnection.prepareStatement("SELECT * FROM categories WHERE category_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet queryResult = preparedStatement.executeQuery();
        
        Category category = null;
        if (queryResult.next()) {
            category = new Category();
            category.setId(id);
            category.setName(queryResult.getString("name"));
            category.setDescription(queryResult.getString("description"));
        }
        return category;
    }
}
