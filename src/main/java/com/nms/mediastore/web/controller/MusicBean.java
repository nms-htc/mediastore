package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.MusicService;
import com.nms.mediastore.entity.Music;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MusicBean extends AbstractBean<Music> {

    private static final long serialVersionUID = -8912312157585232815L;

    @EJB
    private MusicService service;

    public MusicBean() {
    }

    @Override
    protected BaseService<Music> getBaseService() {
        return service;
    }
}
