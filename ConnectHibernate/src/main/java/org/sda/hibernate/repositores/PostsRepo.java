package org.sda.hibernate.repositores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import org.sda.hibernate.entities.Posts;
import org.sda.hibernate.entities.Users;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostsRepo {

    @PersistenceContext(name = "HypersistenceOptimizer")
    private EntityManager entityManager;

    public void savePost(Posts post, Users user) {
        try {
        post.setUserId(user); //Giving the post userId the value of user Id
        post.setDateCreated(LocalDateTime.now());
        post.setDateModified(LocalDateTime.now());

        entityManager.persist(post);
        System.out.println("Post Added!");
        } catch (Exception e) {
            System.out.println("Invalid user / post information!");
        }
    }

    public  void updatePost(Integer postId, String newTitle, String newBody) {

        Posts existingPost = entityManager.find(Posts.class, postId);

        if (existingPost != null) {
            existingPost.setTitle(newTitle);
            existingPost.setBody(newBody);
            existingPost.setDateModified(LocalDateTime.now());

            entityManager.merge(existingPost);
        }
    }

    public Posts findPostById(Integer id) {
        TypedQuery<Posts> findPostIdQuery = entityManager
                .createQuery("SELECT * FROM posts WHERE id = :id", Posts.class);

        findPostIdQuery.setParameter("id", id);
        return findPostIdQuery.getSingleResult();
    }

    public List<Posts> findAll() {
        TypedQuery<Posts> findAllQuery = entityManager
                .createQuery("SELECT * FROM posts", Posts.class);

        return findAllQuery.getResultList();
    }

    public void deletePost(Posts post) {
        entityManager.remove(post);
        System.out.println("Post Deleted!");
    }
}
