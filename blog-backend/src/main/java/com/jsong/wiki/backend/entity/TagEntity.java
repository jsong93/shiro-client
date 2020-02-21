package com.jsong.wiki.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag")
public class TagEntity {
    private int id;
    private String tagName;
    private Integer createdTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
        TagEntity tagEntity = (TagEntity) o;
        return id == tagEntity.id &&
                Objects.equals(tagName, tagEntity.tagName) &&
                Objects.equals(createdTime, tagEntity.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName, createdTime);
    }
}
