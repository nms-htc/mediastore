/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.convertor;

import com.nms.mediastore.ejb.ArtistFacadeLocalBean;
import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.entity.Artist;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Artist.class, value = "artistConvertor")
public class ArtistConvertor extends AbstractEntityConvertor<Artist, Long> {
    
    @EJB
    private ArtistFacadeLocalBean service;

    @Override
    protected BaseService<Artist, Long> getBaseService() {
        return service;
    }

    @Override
    protected Long getKey(String keyStr) {
        return Long.parseLong(keyStr);
    }

    @Override
    protected String getStringKey(Artist entity) {
        return String.valueOf(entity.getArtistId());
    }

}
