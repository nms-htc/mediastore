/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.service;

import com.nms.mediastore.entity.Video;
import java.util.List;

/**
 *
 * @author richard
 */
public interface VideoService extends BaseService<Video> {

    public List<Video> getHotVideo(int count);

    public void setDefault(Video video);

    public Video getDefault();
}
