package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.entity.Artist;
import com.nms.mediastore.model.ArtistLazyDataModel;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@SessionScoped
public class ArtistBean extends AbstractBean<Artist, Long> {

    private static final long serialVersionUID = -7081995377651933248L;

    @EJB
    private ArtistService service;

    @Override
    protected Artist initEntity() {
        return new Artist();
    }

    @Override
    protected BaseService<Artist, Long> getBaseService() {
        return service;
    }

    @Override
    protected LazyDataModel<Artist> initDataModel() {
        return new ArtistLazyDataModel(service);
    }

}
