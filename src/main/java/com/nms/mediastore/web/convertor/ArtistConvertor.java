/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.convertor;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.entity.Artist;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Artist.class, value = "artistConvertor")
public class ArtistConvertor extends AbstractEntityConvertor<Artist> {
    
    @EJB
    private ArtistService service;

    @Override
    protected BaseService<Artist> getBaseService() {
        return service;
    }

    @Override
    protected Class<Artist> getEntityClass() {
        return Artist.class;
    }
    
    
}
