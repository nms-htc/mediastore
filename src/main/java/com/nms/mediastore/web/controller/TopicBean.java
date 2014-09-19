package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.TopicService;
import com.nms.mediastore.entity.Topic;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TopicBean extends AbstractBean<Topic> {

    private static final long serialVersionUID = 3377965103066981773L;

    @EJB
    private TopicService service;

    public TopicBean() {
    }

    @Override
    protected BaseService<Topic> getBaseService() {
        return service;
    }

}
