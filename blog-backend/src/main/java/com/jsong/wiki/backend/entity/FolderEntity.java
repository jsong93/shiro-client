package com.jsong.wiki.backend.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "folder")
// 开启自动插入创建时间 修改时间支持
@EntityListeners(AuditingEntityListener.class)
public class FolderEntity {
    private int id;
    private String isSecret;
    private Date createdTime;
    private Collection<ArticleEntity> articlesById;
    private Date modifiedTime;
    private String folderName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @CreatedDate
    @Column(name = "created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderEntity that = (FolderEntity) o;
        return id == that.id &&
                Objects.equals(folderName, that.folderName) &&
                Objects.equals(isSecret, that.isSecret) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, folderName, isSecret, createdTime);
    }

    @OneToMany(mappedBy = "floderByFloderId")
    public Collection<ArticleEntity> getArticlesById() {
        return articlesById;
    }

    public void setArticlesById(Collection<ArticleEntity> articlesById) {
        this.articlesById = articlesById;
    }

    @Basic
    @LastModifiedDate
    @Column(name = "modified_time")
    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Basic
    @Column(name = "folder_name")
    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
