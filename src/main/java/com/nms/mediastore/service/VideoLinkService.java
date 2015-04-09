/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.service;

import com.nms.mediastore.entity.VideoLink;
import java.util.List;

/**
 *
 * @author richard
 */
public interface VideoLinkService extends BaseService<VideoLink> {

    public List<String> getHotVideoLink();

    public VideoLink findByUUID(String uuid);
}
