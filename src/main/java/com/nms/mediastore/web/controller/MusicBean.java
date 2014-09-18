package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.MusicService;
import com.nms.mediastore.entity.Music;
import com.nms.mediastore.model.MusicLazyDataModel;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@SessionScoped
public class MusicBean extends AbstractBean<Music, Long> {

    private static final long serialVersionUID = -8912312157585232815L;

    @EJB
    private MusicService service;

    public MusicBean() {
    }

    @Override
    protected Music initEntity() {
        return new Music();
    }

    @Override
    protected LazyDataModel<Music> initDataModel() {
        return new MusicLazyDataModel(service);
    }

    @Override
    protected BaseService<Music, Long> getBaseService() {
        return service;
    }

}
