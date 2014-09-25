package com.nms.mediastore.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_MUSIC")
@XmlRootElement
public class Music extends ThumbnailEntity {

    private static final long serialVersionUID = 3721867941446196307L;

    @Column(name = "ISHOT")
    protected Boolean hot;

    @ManyToOne
    @JoinColumn(name = "ARTISTID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected Artist artist;

    @ManyToOne
    @JoinColumn(name = "USERID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected User user;

    @ManyToOne
    @JoinColumn(name = "SINGERID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected Singer singer;

    @ManyToMany
    @JoinTable(name = "MS_MUSICTOPIC")
    protected Set<Topic> topics;
    
    protected FileEntry musicFile;

    public Music() {
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

    public FileEntry getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(FileEntry musicFile) {
        this.musicFile = musicFile;
    }
}
