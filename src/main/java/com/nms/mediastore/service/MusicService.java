package com.nms.mediastore.service;

import com.nms.mediastore.entity.Music;
import java.util.List;

public interface MusicService extends BaseService<Music> {
    
    public List<Music> getHotMusic(int count);

}
