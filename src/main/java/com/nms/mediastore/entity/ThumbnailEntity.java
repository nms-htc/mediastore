package com.nms.mediastore.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.servlet.http.Part;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class ThumbnailEntity extends BaseEntity {

    private static final long serialVersionUID = 4434085396198245523L;

    @Transient
    private Part thumbnailPart;
    
    @Size(max = 300)
    @Column(name = "THUMBNAILPATH")
    private String thumbnailPath;

    public Part getThumbnailPart() {
        return thumbnailPart;
    }

    public void setThumbnailPart(Part thumbnailPart) {
        this.thumbnailPart = thumbnailPart;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
