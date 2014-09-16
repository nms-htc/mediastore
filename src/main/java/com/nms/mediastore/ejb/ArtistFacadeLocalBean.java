package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.Artist;
import javax.ejb.Local;

@Local
public interface ArtistFacadeLocalBean extends BaseService<Artist, Long> {

}
