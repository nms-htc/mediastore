/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.model;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.entity.Artist;

public class ArtistLazyDataModel extends AbstractLazyDataModel<Artist, Long> {

    private static final long serialVersionUID = 3924263155356353360L;

    public ArtistLazyDataModel(BaseService<Artist, Long> baseService) {
        super(baseService);
    }

    @Override
    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public Object getRowKey(Artist object) {
        return object.getArtistId();
    }
}
