package repositories;

import databaseConnection.SqlConnection;
import models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsersRepo {

    private Connection sqlConnection;

    private static final String INSERT_USER_INFO = "INSERT INTO users " +
            "(username, email, password, date_created, date_modified) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_USER_BYID = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String UPDATE_USER_BYID = "UPDATE users SET username = '?', email = '?', password = '?', date_modified = '?' WHERE id = ?";
    private static final String DELETE_USER_BYID = "DELETE * FROM users WHERE id = ?";

    public UsersRepo() {
        try {
            this.sqlConnection = SqlConnection.getSqlConnection();
        } catch (Exception e) {
            System.out.println("Couldn't connect to Database!");
        }
    }

    private static Users toFindUserModel(ResultSet resultSet) throws SQLException {
        Users user = new Users();

        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getNString(2));
        user.setEmail(resultSet.getNString(3));
        user.setPassword(resultSet.getNString(4));
        user.setDateCreated(resultSet.getObject(5, LocalDateTime.class));
        user.setDateModified(resultSet.getObject(6, LocalDateTime.class));

        return user;
    }

    public void insertUser(Users user) throws SQLException {
        PreparedStatement insertUserStatement = sqlConnection.prepareStatement(INSERT_USER_INFO);

        insertUserStatement.setString(1, user.getUsername());
        insertUserStatement.setString(2, user.getEmail());
        insertUserStatement.setString(3, user.getPassword());
        insertUserStatement.setObject(4, LocalDateTime.now());
        insertUserStatement.setObject(5, LocalDateTime.now());

        insertUserStatement.executeUpdate();
        System.out.println("Element Inserted!");
    }

    public Users findById(Integer id) throws SQLException {
        Users user = new Users();
        PreparedStatement selectUserStatement = sqlConnection.prepareStatement(FIND_USER_BYID);
        selectUserStatement.setInt(1, id);

        ResultSet resultSet = selectUserStatement.executeQuery();

        if (resultSet.next()) {
            user = toFindUserModel(resultSet);
        }

        return user;
    }

    public List<Users> findAll() throws SQLException {
        List<Users> userList = new ArrayList<>();
        PreparedStatement selectAllUsers = sqlConnection.prepareStatement(FIND_ALL);

        ResultSet resultSet = selectAllUsers.executeQuery();

        while (resultSet.next()) {
            Users user;
            user = toFindUserModel(resultSet);

            userList.add(user);
        }

        return userList;
    }

    public void updateUser(Integer id, Users user) throws SQLException {
        PreparedStatement updateUserIdStatement = sqlConnection.prepareStatement(UPDATE_USER_BYID);

        updateUserIdStatement.setString(1, user.getUsername());
        updateUserIdStatement.setString(2, user.getEmail());
        updateUserIdStatement.setString(3, user.getPassword());
        updateUserIdStatement.setObject(4, LocalDateTime.now());
        updateUserIdStatement.setInt(5, id);

        int rowUpdated = updateUserIdStatement.executeUpdate();

        if (rowUpdated > 0) {
            System.out.println("Row with Id: " + id + ", updated!");
        }
    }

    public void deleteUserById(Integer id) throws SQLException {
        PreparedStatement deleteUserIdStatement = sqlConnection.prepareStatement(DELETE_USER_BYID);
        deleteUserIdStatement.setInt(1, id);

        deleteUserIdStatement.executeUpdate();
        System.out.println("Row with id: " + id + " Deleted!");
    }

}
