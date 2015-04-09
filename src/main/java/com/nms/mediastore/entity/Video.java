/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "MS_VIDEO")
@NamedQueries({
    @NamedQuery(name = "Video.getDefault", query = "SELECT v FROM Video v WHERE v.defaultVideo = TRUE")
})
@XmlRootElement
public class Video extends ThumbnailEntity {

    private static final long serialVersionUID = -2512796556377496463L;
    
    @Column(name = "ISHOT")
    protected Boolean hot;
    
    @Column(name = "ISDEFAULT")
    protected Boolean defaultVideo;

    @ManyToOne
    @JoinColumn(name = "USERID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected User user;
    
    @ManyToOne
    @JoinColumn(name = "CATEGORYID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected VideoCategory category;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "MUSIC_FILENAME")),
        @AttributeOverride(name = "contentType", column = @Column(name = "MUSIC_CONTENTTYPE")),
        @AttributeOverride(name = "uri", column = @Column(name = "MUSIC_URI")),
        @AttributeOverride(name = "fileSize", column = @Column(name = "MUSIC_FILESIZE"))
    })
    protected FileEntry videoFile;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUBLISHDATE")
    protected Date publishDate = new Date();

    public Video() {
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

    public Boolean getDefaultVideo() {
        return defaultVideo;
    }

    public void setDefaultVideo(Boolean defaultVideo) {
        this.defaultVideo = defaultVideo;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public VideoCategory getCategory() {
        return category;
    }

    public void setCategory(VideoCategory category) {
        this.category = category;
    }

    public FileEntry getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(FileEntry videoFile) {
        this.videoFile = videoFile;
    }
    
}
