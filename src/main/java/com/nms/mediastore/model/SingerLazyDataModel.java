/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.model;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.entity.Singer;

public class SingerLazyDataModel extends AbstractLazyDataModel<Singer, Long> {

    private static final long serialVersionUID = -3238081961075138945L;

    public SingerLazyDataModel(BaseService<Singer, Long> baseService) {
        super(baseService);
    }

    @Override
    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public Object getRowKey(Singer object) {
        return object.getSingerId();
    }

}
