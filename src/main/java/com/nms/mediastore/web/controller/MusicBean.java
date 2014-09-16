package com.nms.mediastore.web.controller;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.ejb.MusicFacadeLocalBean;
import com.nms.mediastore.entity.Music;
import com.nms.mediastore.model.MusicLazyDataModel;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@SessionScoped
public class MusicBean extends AbstractManagedBean<Music, Long> {

    private static final long serialVersionUID = -8912312157585232815L;

    @EJB
    private MusicFacadeLocalBean service;

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
