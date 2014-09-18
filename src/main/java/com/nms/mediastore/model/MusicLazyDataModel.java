/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.model;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.entity.Music;

public class MusicLazyDataModel extends AbstractLazyDataModel<Music, Long> {

    private static final long serialVersionUID = -1178217953687604121L;

    public MusicLazyDataModel(BaseService<Music, Long> baseService) {
        super(baseService);
    }

    @Override
    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public Object getRowKey(Music object) {
        return object.getMusicId();
    }

}
