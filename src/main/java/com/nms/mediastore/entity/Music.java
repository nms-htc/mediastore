package com.nms.mediastore.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_MUSIC")
@NamedQueries({
    @NamedQuery(name = "Music.getDefault", query = "SELECT m FROM Music m WHERE m.defaultMusic = TRUE")
})
@XmlRootElement
public class Music extends ThumbnailEntity {

    private static final long serialVersionUID = 3721867941446196307L;

    @Column(name = "ISHOT")
    protected Boolean hot;
    
    @Column(name = "ISDEFAULT")
    protected Boolean defaultMusic;

    @ManyToOne
    @JoinColumn(name = "ARTISTID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected Artist writer;

    @ManyToOne
    @JoinColumn(name = "USERID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected User user;

    @ManyToOne
    @JoinColumn(name = "SINGERID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected Artist singer;

    @ManyToMany
    @JoinTable(name = "MS_MUSICTOPIC")
    protected List<Topic> topics;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "MUSIC_FILENAME")),
        @AttributeOverride(name = "contentType", column = @Column(name = "MUSIC_CONTENTTYPE")),
        @AttributeOverride(name = "uri", column = @Column(name = "MUSIC_URI")),
        @AttributeOverride(name = "fileSize", column = @Column(name = "MUSIC_FILESIZE"))
    })
    protected FileEntry musicFile;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUBLISHDATE")
    protected Date publishDate = new Date();

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

    public Artist getWriter() {
        return writer;
    }

    public void setWriter(Artist writer) {
        this.writer = writer;
    }

    public Artist getSinger() {
        return singer;
    }

    public void setSinger(Artist singer) {
        this.singer = singer;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public FileEntry getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(FileEntry musicFile) {
        this.musicFile = musicFile;
    }

    public Boolean getDefaultMusic() {
        return defaultMusic;
    }

    public void setDefaultMusic(Boolean defaultMusic) {
        this.defaultMusic = defaultMusic;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
