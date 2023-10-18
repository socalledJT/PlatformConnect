package repositories;

import databaseConnection.SqlConnection;
import models.Posts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PostsRepo {

    private Connection sqlConnection;

    private static final String INSERT_POST_INFO = "INSERT INTO posts " +
            "(title, body, user_id, date_created, date_modified) " +
            "values (?, ?, ?, ?, ?)";
    private static final String TRANFER_USER_ID = "SELECT id FROM users WHERE username = '?'";
    private static final String FIND_POST_BYID = "SELECT * FROM posts WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM posts";
    private static final String UPDATE_POST_BYID = "UPDATE posts SET title = '?', body = '?', date_modified = '?' WHERE id = ?";
    private static final String DELETE_POSTS_BYTITLE = "DELETE * FROM posts WHERE title = '?'";

    public PostsRepo() {
        try {
            this.sqlConnection = SqlConnection.getSqlConnection();
        } catch (Exception e) {
            System.out.println("Couldn't connect!");
        }
    }

    private static Posts toFindPostModel(ResultSet resultSet) throws SQLException {
        Posts post = new Posts();

        post.setId(resultSet.getInt("id"));
        post.setTitle(resultSet.getString("title"));
        post.setBody(resultSet.getString("body"));
        post.setUserId(resultSet.getInt("user_id"));
        post.setDateCreated(resultSet.getObject("date_created", LocalDateTime.class));
        post.setDateModified(resultSet.getObject("date_modified", LocalDateTime.class));

        return post;
    }

    private Integer tranferUserId(String username) throws SQLException {

        PreparedStatement tranferIdStatement = sqlConnection.prepareStatement(TRANFER_USER_ID);
        tranferIdStatement.setString(1, username);

        ResultSet resultSet = tranferIdStatement.executeQuery();

        return resultSet.getInt("id");
    }

    public void insertPost(Posts post, String username) throws SQLException {
        Integer toTransfer = tranferUserId(username);

        PreparedStatement insertPostStatement = sqlConnection.prepareStatement(INSERT_POST_INFO);
        insertPostStatement.setString(1, post.getTitle());
        insertPostStatement.setString(2, post.getBody());
        insertPostStatement.setInt(3, toTransfer);
        insertPostStatement.setObject(4, LocalDateTime.now());
        insertPostStatement.setObject(5, LocalDateTime.now());

        insertPostStatement.executeUpdate();
        System.out.println("Post Inserted!");
    }

    public Posts findById(Integer id) throws SQLException {
        Posts post = new Posts();

        PreparedStatement selectPostStatement = sqlConnection.prepareStatement(FIND_POST_BYID);
        selectPostStatement.setInt(1, id);

        ResultSet resultSet = selectPostStatement.executeQuery();

        if (resultSet.next()) {
            post = toFindPostModel(resultSet);
        }

        return post;
    }

    public List<Posts> findAll() throws SQLException {
        List<Posts> postsList = new ArrayList<>();
        PreparedStatement selectAllPosts = sqlConnection.prepareStatement(FIND_ALL);

        ResultSet resultSet = selectAllPosts.executeQuery();

        while (resultSet.next()) {
            Posts post;
            post = toFindPostModel(resultSet);

            postsList.add(post);
        }

        return postsList;
    }

    public void updatePost(Integer id, Posts post) throws SQLException {
        PreparedStatement updatePostIdStatement = sqlConnection.prepareStatement(UPDATE_POST_BYID);

        updatePostIdStatement.setString(1, post.getTitle());
        updatePostIdStatement.setString(2, post.getBody());
        updatePostIdStatement.setObject(3, LocalDateTime.now());
        updatePostIdStatement.setInt(4, id);

        int rowUpdated = updatePostIdStatement.executeUpdate();

        if (rowUpdated > 0) {
            System.out.println("Row with id: " + id + ", updated!");
        }

    }

    public void deletePostByid(Integer id) throws SQLException {
        PreparedStatement deletePostBytitleStatement = sqlConnection.prepareStatement(DELETE_POSTS_BYTITLE);
        deletePostBytitleStatement.setInt(1, id);

        deletePostBytitleStatement.executeUpdate();
        System.out.println("Row with id: " + id + " Deleted!");
    }

}
