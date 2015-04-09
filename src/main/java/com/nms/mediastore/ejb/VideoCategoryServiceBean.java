/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.VideoCategory;
import com.nms.mediastore.service.VideoCategoryService;
import javax.ejb.Stateless;

/**
 *
 * @author richard
 */
@Stateless
public class VideoCategoryServiceBean extends AbstractService<VideoCategory> implements VideoCategoryService {

    private static final long serialVersionUID = -424433971919084171L;

    public VideoCategoryServiceBean() {
        super(VideoCategory.class);
    }

}
