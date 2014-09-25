package com.nms.mediastore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7135956310377724296L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected Long id;
    
    @Size(max = 150)
    @Column(name = "TITLE")
    protected String title;
    
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    protected String description;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDDATE")
    protected Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIEDDATE")
    protected Date modifiedDate;

    public BaseEntity() {
    }
    
    @PrePersist
    protected void onPrePersist() {
        Date now = new Date();
        createdDate = now;
        modifiedDate = now;
    }
    
    @PreUpdate
    protected void onPreUpdate() {
        modifiedDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        if (title != null && !title.trim().isEmpty()) {
            return title;
        }
        return super.toString();
    }
}
