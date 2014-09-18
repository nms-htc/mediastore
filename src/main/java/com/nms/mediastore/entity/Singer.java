/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MS_SINGER")
@XmlRootElement
public class Singer implements Serializable {

    private static final long serialVersionUID = 3052122354694036724L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SINGERID")
    private Long singerId;

    @NotNull
    @Size(max = 150)
    @Column(name = "NAME")
    private String name;

    @Size(max = 200)
    @Column(name = "IMAGEURI")
    private String imageUri;

    @Lob
    private String description;

    public Singer() {
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Singer other = (Singer) obj;
        return Objects.equals(this.singerId, other.singerId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.singerId);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
