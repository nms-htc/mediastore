package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.Music;
import javax.ejb.Local;

@Local
public interface MusicFacadeLocalBean extends BaseService<Music, Long> {

}
