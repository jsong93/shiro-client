package com.jsong.wiki.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "floder")
public class FloderEntity {
    private int id;
    private String floderName;
    private String isSecret;
    private Integer createdTime;
    private Collection<ArticleEntity> articlesById;
    private Integer modifiedTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "floder_name")
    public String getFloderName() {
        return floderName;
    }

    public void setFloderName(String floderName) {
        this.floderName = floderName;
    }

    @Basic
    @Column(name = "is_secret")
    public String getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(String isSecret) {
        this.isSecret = isSecret;
    }

    @Basic
    @Column(name = "created_time")
    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloderEntity that = (FloderEntity) o;
        return id == that.id &&
                Objects.equals(floderName, that.floderName) &&
                Objects.equals(isSecret, that.isSecret) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, floderName, isSecret, createdTime);
    }

    @OneToMany(mappedBy = "floderByFloderId")
    public Collection<ArticleEntity> getArticlesById() {
        return articlesById;
    }

    public void setArticlesById(Collection<ArticleEntity> articlesById) {
        this.articlesById = articlesById;
    }

    @Basic
    @Column(name = "modified_time")
    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
