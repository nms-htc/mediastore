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

    public Singer() {
    }
}
