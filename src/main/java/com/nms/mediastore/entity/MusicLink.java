/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_MUSICLINK")
@XmlRootElement
public class MusicLink implements Serializable {

    private static final long serialVersionUID = -7153926835169422111L;

    @Id
    @TableGenerator(
            name = "linkSQ",
            table = "MS_SEQUENCE",
            pkColumnName = "NAME",
            pkColumnValue = "VALUE",
            valueColumnName = "MUSICLINK_SQ"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "linkSQ")
    @Column(name = "LINKID")
    private Long linkId;
    @ManyToOne
    @JoinColumn(name = "MUSICID",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Music music;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    public MusicLink() {
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MusicLink other = (MusicLink) obj;
        return Objects.equals(this.linkId, other.linkId);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.linkId);
        return hash;
    }

}
