package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.entity.Artist;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ArtistBean extends AbstractBean<Artist> {

    private static final long serialVersionUID = -7081995377651933248L;

    @EJB
    private ArtistService service;

    @Override
    protected BaseService<Artist> getBaseService() {
        return service;
    }

    @Override
    protected Artist initEntity() {
        return new Artist();
    }
    
}
