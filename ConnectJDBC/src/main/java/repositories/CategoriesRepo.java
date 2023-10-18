package repositories;

import databaseConnection.SqlConnection;
import models.Categories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepo {

    private Connection sqlConnection;

    private static final String INSERT_CATEGORY_INFO = "INSERT INTO categories (name, date_created, date_modified) VALUES (?, ?, ?)";
    private static final String FIND_CATEGORY_BYID = "SELECT * FROM categories WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM categories";
    private static final String UPDATE_CATEGORY_BYID = "UPDATE categories SET name = '?', date_modified = '?' WHERE id = ?";
    private static final String DELETE_CATEGORY_BYID = "DELETE * FROM categories WHERE id = ?";

    public CategoriesRepo() {
        try {
            this.sqlConnection = SqlConnection.getSqlConnection();
        } catch (Exception e) {
            System.out.println("Couldn't connect!");
        }
    }

    private static Categories toFindCategoryModel(ResultSet resultSet) throws SQLException {
        Categories category = new Categories();

        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setDateCreated(resultSet.getObject("date_created", LocalDateTime.class));
        category.setDateModified(resultSet.getObject("date_modified", LocalDateTime.class));

        return category;
    }

    public void insertCategory(Categories category) throws SQLException {
        PreparedStatement insertCategoryStatement = sqlConnection.prepareStatement(INSERT_CATEGORY_INFO);
        insertCategoryStatement.setString(1, category.getName());
        insertCategoryStatement.setObject(2, LocalDateTime.now());
        insertCategoryStatement.setObject(3, LocalDateTime.now());

        insertCategoryStatement.executeUpdate();
        System.out.println("Category Added!");
    }

    public Categories findById(Integer id) throws SQLException {
        Categories category = new Categories();
        PreparedStatement selectCategoryStatement = sqlConnection.prepareStatement(FIND_CATEGORY_BYID);
        selectCategoryStatement.setInt(1, id);

        ResultSet resultSet = selectCategoryStatement.executeQuery();

        if (resultSet.next()) {
            category = toFindCategoryModel(resultSet);
        }

        return category;
    }

    public List<Categories> findAll() throws SQLException {
        List<Categories> categoryList = new ArrayList<>();

        PreparedStatement selectAllCategories = sqlConnection.prepareStatement(FIND_ALL);
        ResultSet resultSet = selectAllCategories.executeQuery();

        while (resultSet.next()) {
            Categories category;
            category = toFindCategoryModel(resultSet);

            categoryList.add(category);
        }

        return categoryList;
    }

    public void updateCategory(Integer id, Categories category) throws SQLException {
        PreparedStatement updateCategoryByIdStatement = sqlConnection.prepareStatement(UPDATE_CATEGORY_BYID);

        updateCategoryByIdStatement.setString(1, category.getName());
        updateCategoryByIdStatement.setObject(2, LocalDateTime.now());
        updateCategoryByIdStatement.setInt(3, id);

        int rowUpdated = updateCategoryByIdStatement.executeUpdate();

        if (rowUpdated > 0) {
            System.out.println("Row with Id: " + id + ", updated!");
        }
    }

    public void deleteCategoryById(Integer id) throws SQLException {
        PreparedStatement deleteCategoryIdstatement = sqlConnection.prepareStatement(DELETE_CATEGORY_BYID);
        deleteCategoryIdstatement.setInt(1, id);

        deleteCategoryIdstatement.executeUpdate();
        System.out.println("Row with id: " + id + " Deleted!");
    }
}
