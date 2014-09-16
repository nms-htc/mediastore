/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_MUSIC")
@XmlRootElement
public class Music implements Serializable {

    private static final long serialVersionUID = 3721867941446196307L;

    @Id
    @TableGenerator(
            name = "sequenceTb",
            table = "MS_SEQUENCE",
            pkColumnName = "NAME",
            pkColumnValue = "VALUE",
            valueColumnName = "MUSIC_SQ"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTb")
    @Column(name = "MUSICID")
    private Long musicId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIEDDATE")
    private Date modifiedDate;

    @NotNull
    @Size(max = 200)
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Size(max = 200)
    @Column(name = "FILEURI")
    private String fileUri;
    
    @Size(max = 200)
    @Column(name = "IMAGEURI")
    private String imageUri;

    @Column(name = "ISHOT")
    private Boolean hot;

    @ManyToOne
    @JoinColumn(name = "ARTISTID")
    private Artist artist;
    
    @ManyToOne
    @JoinColumn(name = "USERID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SINGERID")
    private Singer singer;

    @ManyToMany
    @JoinTable(name = "MS_MUSICTOPIC")
    private Set<Topic> topics;

    public Music() {
        Date now = new Date();
        createDate = now;
        modifiedDate = now;
    }

    public Long getMusicId() {
        return musicId;
    }

    public void setMusicId(Long musicId) {
        this.musicId = musicId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }
    
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Music other = (Music) obj;
        return Objects.equals(this.musicId, other.musicId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.musicId ^ (this.musicId >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return title;
    }
    
}
