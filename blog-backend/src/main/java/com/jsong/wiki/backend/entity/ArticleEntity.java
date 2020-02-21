package com.jsong.wiki.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "article")
public class ArticleEntity {
    private int id;
    private String title;
    private String article;
    private Integer createdTime;
    private Integer modifliedTime;
    private String tag;
    private String isSecret;
    private FloderEntity floderByFloderId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "article")
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Basic
    @Column(name = "created_time")
    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "modiflied_time")
    public Integer getModifliedTime() {
        return modifliedTime;
    }

    public void setModifliedTime(Integer modifliedTime) {
        this.modifliedTime = modifliedTime;
    }

    @Basic
    @Column(name = "tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "is_secret")
    public String getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(String isSecret) {
        this.isSecret = isSecret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(article, that.article) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifliedTime, that.modifliedTime) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(isSecret, that.isSecret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, article, createdTime, modifliedTime, tag, isSecret);
    }

    @ManyToOne
    @JoinColumn(name = "floderId", referencedColumnName = "id")
    public FloderEntity getFloderByFloderId() {
        return floderByFloderId;
    }

    public void setFloderByFloderId(FloderEntity floderByFloderId) {
        this.floderByFloderId = floderByFloderId;
    }
}
