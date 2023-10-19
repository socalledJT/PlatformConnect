package org.sda.hibernate.repositores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import org.sda.hibernate.entities.Categories;
import org.sda.hibernate.entities.PostCategories;
import org.sda.hibernate.entities.Posts;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostCategoriesRepo {

    @PersistenceContext(name = "HypersistenceOptimizer")
    private EntityManager entityManager;

    public void savePostCategory(PostCategories postCategory, Posts post, Categories category) {
        postCategory.setPostId(post);
        postCategory.setCategoryId(category);
        postCategory.setDateCreated(LocalDateTime.now());
        postCategory.setDateModified(LocalDateTime.now());

        entityManager.persist(postCategory);
        System.out.println("Post Category created!");
    }

    public void updatePostCategory(PostCategories postCategory) {
        postCategory.setDateModified(LocalDateTime.now());

        entityManager.merge(postCategory);
    }

    public PostCategories findById(Integer id) {
        TypedQuery<PostCategories> findByIdQuery = entityManager
                .createQuery("SELECT * FROM post_categories WHERE id = :id", PostCategories.class);
        findByIdQuery.setParameter("id", id);
        return findByIdQuery.getSingleResult();
    }

    public List<PostCategories> findAll() {
        TypedQuery<PostCategories> findAllQuery = entityManager
                .createQuery("SELECT * FROM post_categories", PostCategories.class);

        return findAllQuery.getResultList();
    }

    public void deletePostCategory(PostCategories postCategory) {
        entityManager.remove(postCategory);
        System.out.println("Post Category removed!");
    }
}
