package org.sda.hibernate;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.sda.hibernate.entities.Users;
import org.sda.hibernate.repositores.UsersRepo;
import org.springframework.boot.SpringApplication;

public class HibernateApplication {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("");

        Users saimir = new Users("@soCalledJt", "@SaimirBrahja@outlook.com", "f1@76sdsahA!");
        UsersRepo userRepo = new UsersRepo();

//        userRepo.saveUser(saimir);
        userRepo.findUserById(1);

        SpringApplication.run(HibernateApplication.class, args);
    }
}
