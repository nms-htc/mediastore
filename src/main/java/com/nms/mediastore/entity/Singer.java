package com.nms.mediastore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_SINGER")
@XmlRootElement
public class Singer extends ThumbnailEntity {

    private static final long serialVersionUID = 3052122354694036724L;

    @Size(max = 200)
    @Column(name = "IMAGEURI")
    private String imageUri;

    public Singer() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
