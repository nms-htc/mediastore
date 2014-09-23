package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class SingerBean extends AbstractThumbnailBean<Singer> {

    private static final long serialVersionUID = 9125920186450221214L;

    @EJB
    private SingerService service;

    @Override
    protected BaseService<Singer> getBaseService() {
        return service;
    }

    @Override
    protected Singer initEntity() {
        return new Singer();
    }
}
