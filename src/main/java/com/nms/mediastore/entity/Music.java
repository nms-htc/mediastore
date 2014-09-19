package com.nms.mediastore.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_MUSIC")
@XmlRootElement
public class Music extends BaseEntity {

    private static final long serialVersionUID = 3721867941446196307L;

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
}
