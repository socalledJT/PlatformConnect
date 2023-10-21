package org.sda;

import databaseConnection.SqlConnection;
import models.Posts;
import models.Users;
import repositories.PostsRepo;
import repositories.UsersRepo;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        Connection connection = SqlConnection.getSqlConnection();

        UsersRepo usersRepo = new UsersRepo();

        Users johnnyTm = new Users("@johnnyTm", "JohnnyTm@gmail.com", "F286s@hsay");
        Users updateJohnny = new Users("@Johnny", "johnytmson@gmail.com", "F256@say");
        Users qaziim = new Users("@qazim", "qazim@email.com", "Daty@i6n1g");
        Users guest = new Users("@guest", "No-Email", "Daty@i6n1g");

//        usersRepo.insertUser(johnnyTm);       //Added
//        usersRepo.insertUser(qaziim);         //Added
//        usersRepo.insertUser(guest);          //Added
//        System.out.println(usersRepo.findById(1));        //User Found
//        usersRepo.updateUser(1, updateJohnny);            //User Updated
//        System.out.println(usersRepo.findAll());          //Users found
//        usersRepo.deleteUserById(3);          //Deleted

        PostsRepo postsRepo = new PostsRepo();

        Posts blog = new Posts("Following thr 5th", "The story of the 5th SS division from creation to...");

        postsRepo.insertPost(blog, qaziim.getUsername());


    }
}