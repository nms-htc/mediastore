/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.convertor;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Singer.class, value = "singerConvertor")
public class SingerConverter extends AbstractEntityConvertor<Singer, Long> {
    
    @EJB
    private SingerService service;

    @Override
    protected BaseService<Singer, Long> getBaseService() {
        return service;
    }

    @Override
    protected Long getKey(String keyStr) {
        return Long.parseLong(keyStr);
    }

    @Override
    protected String getStringKey(Singer entity) {
        return String.valueOf(entity.getSingerId());
    }
    
}
