/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "MS_VIDEO_CAT")
@XmlRootElement
public class VideoCategory extends BaseEntity {

    private static final long serialVersionUID = -6898583342946892397L;

}
