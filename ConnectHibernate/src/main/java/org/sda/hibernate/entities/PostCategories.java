package org.sda.hibernate.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_categories")
public class PostCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "post_id")
    private Posts postId;
    @Column(name = "category_id")
    private Categories categoryId;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    public PostCategories() {

    }

    public PostCategories(Integer id, Posts postId, Categories categoryId, LocalDateTime dateCreated, LocalDateTime dateModified) {
        this.id = id;
        this.postId = postId;
        this.categoryId = categoryId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Posts getPostId() {
        return postId;
    }

    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "PostCategories{" +
                "id=" + id +
                ", postId=" + postId +
                ", categoryId=" + categoryId +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                '}';
    }
}
