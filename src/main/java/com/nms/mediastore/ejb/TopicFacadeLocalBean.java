package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.Topic;
import javax.ejb.Local;

@Local
public interface TopicFacadeLocalBean extends BaseService<Topic, Long> {
 
}
