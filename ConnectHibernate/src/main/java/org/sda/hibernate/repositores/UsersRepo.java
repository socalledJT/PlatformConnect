package org.sda.hibernate.repositores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import org.sda.hibernate.entities.Users;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UsersRepo {

    @PersistenceContext(name = "HypersistenceOptimizer")
    private EntityManager entityManager;

    public void saveUser(Users user) {
        user.setDateCreated(LocalDateTime.now());
        user.setDateModified(LocalDateTime.now());
        entityManager.persist(user);
        System.out.println("User Added!");
    }

    public void updateUser(Users user) {
        user.setDateModified(LocalDateTime.now());

        entityManager.merge(user);
        System.out.println("User Updated!");
    }

    public Users findUserById(Integer id) {
        TypedQuery<Users> findUserByIdQuery = entityManager
                .createQuery("SELECT * FROM users WHERE id = :id", Users.class);

        findUserByIdQuery.setParameter("id", id);
        return  findUserByIdQuery.getSingleResult();
    }

    public List<Users> findAll() {
        TypedQuery<Users> findAllQuery = entityManager
                .createQuery("SELECT * FROM users", Users.class);

        return findAllQuery.getResultList();
    }

    public void deleteUser(Users user) {
        entityManager.remove(user);
        System.out.println("User Removed!");
    }
}
