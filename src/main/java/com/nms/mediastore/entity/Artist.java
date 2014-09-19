package com.nms.mediastore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_ARTIST")
@XmlRootElement
public class Artist extends BaseEntity {

    private static final long serialVersionUID = -8825686824596189837L;

    @Size(max = 200)
    @Column(name = "IMAGEURI")
    private String imageUri;

    public Artist() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
