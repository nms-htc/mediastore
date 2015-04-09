/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.convertor;

import com.nms.mediastore.entity.VideoCategory;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.VideoCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author richard
 */
@FacesConverter(forClass = VideoCategory.class, value = "videoConverter")
public class VideoCategoryConverter extends AbstractEntityConvertor<VideoCategory> {

    @EJB
    private VideoCategoryService categoryService;

    @Override
    protected BaseService<VideoCategory> getBaseService() {
        return this.categoryService;
    }

    @Override
    protected Class<VideoCategory> getEntityClass() {
        return VideoCategory.class;
    }
    
}
