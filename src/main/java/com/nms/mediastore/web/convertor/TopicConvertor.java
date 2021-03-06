/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.convertor;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.TopicService;
import com.nms.mediastore.entity.Topic;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Topic.class, value = "topicConvertor")
public class TopicConvertor extends AbstractEntityConvertor<Topic> {

    @EJB
    private TopicService service;

    @Override
    protected BaseService<Topic> getBaseService() {
        return service;
    }

    @Override
    protected Class<Topic> getEntityClass() {
        return Topic.class;
    }
}
