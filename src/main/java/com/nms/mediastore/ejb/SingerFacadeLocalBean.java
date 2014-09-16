package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.Singer;
import javax.ejb.Local;

@Local
public interface SingerFacadeLocalBean extends BaseService<Singer, Long> {

}
