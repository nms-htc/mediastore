package com.nms.mediastore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_TOPIC")
@XmlRootElement
public class Topic extends BaseEntity {

    private static final long serialVersionUID = 1131759164045421326L;

    @Size(max = 200)
    @Column(name = "IMAGEURI")
    private String imageUri;

    public Topic() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
