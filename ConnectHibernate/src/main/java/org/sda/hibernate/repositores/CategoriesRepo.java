package org.sda.hibernate.repositores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import org.sda.hibernate.entities.Categories;
import org.springframework.stereotype.Repository;

import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CategoriesRepo {

    @PersistenceContext(name = "HypersistenceOptimizer")
    private EntityManager entityManager;

    public void saveCategory(Categories category) {
        category.setDateCreated(LocalDateTime.now());
        entityManager.persist(category);
        System.out.println("Category Added!");
    }

    public void updateCategory(Categories category) {
        category.setDateModified(LocalDateTime.now());
        entityManager.merge(category);

        System.out.println("Category Updated!");
    }

    public Categories findCategoryById(Integer id) {
        TypedQuery<Categories> findCategoryByIdQuery = entityManager
                .createQuery("SELECT * FROM categories WHERE id = :id", Categories.class);
        findCategoryByIdQuery.setParameter("id", id);

        return findCategoryByIdQuery.getSingleResult();
    }

    public List<Categories> findAll() {
        TypedQuery<Categories> findAllQuery = entityManager
                .createQuery("SELECT * FROM categories", Categories.class);

        return findAllQuery.getResultList();
    }

    public void deleteCategory(Categories category) {
        entityManager.remove(category);
        System.out.println("Category Removed!");
    }
}
