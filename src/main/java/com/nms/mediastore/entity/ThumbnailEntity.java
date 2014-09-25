package com.nms.mediastore.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ThumbnailEntity extends BaseEntity {

    private static final long serialVersionUID = 4434085396198245523L;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "THUMBNAIL_FILENAME")),
        @AttributeOverride(name = "contentType", column = @Column(name = "THUMBNAIL_CONTENTTYPE")),
        @AttributeOverride(name = "uri", column = @Column(name = "THUMBNAIL_URI")),
        @AttributeOverride(name = "fileSize", column = @Column(name = "THUMBNAIL_FILESIZE"))
    })
    protected FileEntry thumbnail;

    public FileEntry getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(FileEntry thumbnail) {
        this.thumbnail = thumbnail;
    }
}
