package models;

import java.time.LocalDateTime;

public class PostCategories {

    private Integer id;
    private Integer postId;
    private Integer categoryId;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public PostCategories() {
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
