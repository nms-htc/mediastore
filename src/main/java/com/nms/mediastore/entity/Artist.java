package com.nms.mediastore.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_ARTIST")
@XmlRootElement
public class Artist extends ThumbnailEntity {

    private static final long serialVersionUID = -8825686824596189837L;
    
}
