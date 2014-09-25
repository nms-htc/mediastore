package com.nms.mediastore.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_TOPIC")
@XmlRootElement
public class Topic extends ThumbnailEntity {

    private static final long serialVersionUID = 1131759164045421326L;

    public Topic() {
    }
}
