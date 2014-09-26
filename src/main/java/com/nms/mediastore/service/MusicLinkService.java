/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.service;

import com.nms.mediastore.entity.MusicLink;
import java.util.List;

/**
 *
 * @author CuongNT
 */
public interface MusicLinkService extends BaseService<MusicLink> {

    public List<String> getHotMusicLink();
    
    public MusicLink findByUUID(String uuid);

}
