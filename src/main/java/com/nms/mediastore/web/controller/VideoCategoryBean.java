/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.VideoCategory;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.VideoCategoryService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author richard
 */
@Named
@ViewScoped
public class VideoCategoryBean extends AbstractBean<VideoCategory> {

    private static final long serialVersionUID = 6157514635129514924L;

    @EJB
    private VideoCategoryService videoCategoryService;

    @Override
    protected VideoCategory initEntity() {
        return new VideoCategory();
    }

    @Override
    protected BaseService<VideoCategory> getBaseService() {
        return videoCategoryService;
    }
}
