package repositories;

import databaseConnection.SqlConnection;
import models.PostCategories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostCategoriesRepo {

    private Connection sqlConnection;

    private static final String INSERT_POSTCATEGORY_INFO = "INSERT INTO post_categories " +
            "(post_id, category_id, date_created, date_modified) " +
            "values (?, ?, ?, ?)";
    private static final String TRANFER_POST_ID = "SELECT id FROM posts WHERE title = ?";
    private static final String TRANFER_CATEGORY_ID = "SELECT id FROM categories WHERE name = ?";
    private static final String FIND_POSTCATEGORY_BYID = "SELECT * FROM post_categories WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM post_categories";

    public PostCategoriesRepo() {
        try {
            this.sqlConnection = SqlConnection.getSqlConnection();
        } catch (Exception e) {
            System.out.println("Couldn't connect!");
        }
    }

    private static PostCategories toFindPostCategoryModel(ResultSet resultSet) throws SQLException {
        PostCategories postCategory = new PostCategories();

        postCategory.setId(resultSet.getInt("id"));
        postCategory.setPostId(resultSet.getInt("post_id"));
        postCategory.setCategoryId(resultSet.getInt("category_id"));
        postCategory.setDateCreated(resultSet.getObject("date_created", LocalDateTime.class));
        postCategory.setDateModified(resultSet.getObject("date_modified", LocalDateTime.class));

        return postCategory;
    }

    private Integer tranferPostId(String title) throws SQLException {

        PreparedStatement tranferIdStatement = sqlConnection.prepareStatement(TRANFER_POST_ID);
        tranferIdStatement.setString(1, title);

        ResultSet resultSet = tranferIdStatement.executeQuery();

        return resultSet.getInt("id");
    }

    private Integer tranferCategoryId(String name) throws SQLException {

        PreparedStatement tranferIdStatement = sqlConnection.prepareStatement(TRANFER_CATEGORY_ID);
        tranferIdStatement.setString(1, name);

        ResultSet resultSet = tranferIdStatement.executeQuery();

        return resultSet.getInt("id");
    }

    public void insertPostCategory(PostCategories postCategory, String title, String name) throws SQLException {
        Integer toTranferPostId = tranferPostId(title);
        Integer toTranferCategoryId = tranferCategoryId(name);

        PreparedStatement insertPostCategoryStatement = sqlConnection.prepareStatement(INSERT_POSTCATEGORY_INFO);
        insertPostCategoryStatement.setInt(1, toTranferPostId);
        insertPostCategoryStatement.setInt(2, toTranferCategoryId);
        insertPostCategoryStatement.setObject(3, LocalDateTime.now());
        insertPostCategoryStatement.setObject(4, LocalDateTime.now());

        insertPostCategoryStatement.executeUpdate();
        System.out.println("Element Inserted!");
    }

    public PostCategories findById(Integer id) throws  SQLException {
        PostCategories postCategory = new PostCategories();

        PreparedStatement selectPostCategoryByIdStatement = sqlConnection.prepareStatement(FIND_POSTCATEGORY_BYID);
        selectPostCategoryByIdStatement.setInt(1, id);

        ResultSet resultSet = selectPostCategoryByIdStatement.executeQuery();

        if (resultSet.next()) {
            postCategory = toFindPostCategoryModel(resultSet);
        }

        return postCategory;
    }

    public List<PostCategories> findAll() throws SQLException {
        List<PostCategories> postCategoriesList = new ArrayList<>();
        PreparedStatement selectAllPostCategories = sqlConnection.prepareStatement(FIND_ALL);

        ResultSet resultSet = selectAllPostCategories.executeQuery();

        while (resultSet.next()) {
            PostCategories postCategory;
            postCategory = toFindPostCategoryModel(resultSet);

            postCategoriesList.add(postCategory);
        }

        return postCategoriesList;
    }
}
