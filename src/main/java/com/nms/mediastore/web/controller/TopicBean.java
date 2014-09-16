package com.nms.mediastore.web.controller;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.ejb.TopicFacadeLocalBean;
import com.nms.mediastore.entity.Topic;
import com.nms.mediastore.model.TopicLazyDataModel;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@SessionScoped
public class TopicBean extends AbstractManagedBean<Topic, Long> {

    private static final long serialVersionUID = 3377965103066981773L;

    @EJB
    private TopicFacadeLocalBean service;

    public TopicBean() {
    }

    @Override
    protected Topic initEntity() {
        return new Topic();
    }

    @Override
    protected LazyDataModel<Topic> initDataModel() {
        return new TopicLazyDataModel(service);
    }

    @Override
    protected BaseService<Topic, Long> getBaseService() {
        return service;
    }

}
