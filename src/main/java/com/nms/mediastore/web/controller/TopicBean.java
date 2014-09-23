package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.TopicService;
import com.nms.mediastore.entity.Topic;
import com.nms.mediastore.entity.User;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TopicBean extends AbstractThumbnailBean<Topic> {

    private static final long serialVersionUID = 3377965103066981773L;

    @EJB
    private TopicService service;

    public TopicBean() {
    }

    @Override
    protected BaseService<Topic> getBaseService() {
        return service;
    }

    @Override
    protected Topic initEntity() {
        return new Topic();
    }

}
