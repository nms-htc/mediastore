/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.io.InputStream;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Embeddable
public class FileEntry implements Serializable {

    private static final long serialVersionUID = -1684493112840078370L;

    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String contentType;
    @Column(length = 300)
    private String uri;
    
    @Transient
    @XmlTransient
    private InputStream inputStream;
    private long fileSize;

    public FileEntry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getSize() {
        return fileSize;
    }

    public void setSize(long size) {
        this.fileSize = size;
    }
}
