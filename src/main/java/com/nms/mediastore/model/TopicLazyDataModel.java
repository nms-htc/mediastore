/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.model;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.entity.Topic;

public class TopicLazyDataModel extends AbstractLazyDataModel<Topic, Long> {

    private static final long serialVersionUID = -1981047352377700983L;

    public TopicLazyDataModel(BaseService<Topic, Long> baseService) {
        super(baseService);
    }

    @Override
    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public Object getRowKey(Topic object) {
        return object.getTopicId();
    }

}
