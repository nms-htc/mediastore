/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "MS_VIDEOLINK")
@NamedQueries({
    @NamedQuery(
            name = "VideoLink.findByUUID",
            query = "SELECT vl FROM VideoLink vl WHERE vl.uuid = :uuid AND vl.expireDate >= CURRENT_TIMESTAMP"
    )
})
@XmlRootElement
public class VideoLink extends BaseEntity {

    private static final long serialVersionUID = 6251822625697673725L;
    
    @PrePersist
    public void generateUUID() {
        uuid = UUID.randomUUID().toString();
    }

    @NotNull
    @Column(name = "UUID", nullable = false, length = 100, unique = true)
    protected String uuid;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "EXPIREDATE", nullable = false)
    protected Date expireDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VIDEOID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected Video video;

    public VideoLink() {
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
