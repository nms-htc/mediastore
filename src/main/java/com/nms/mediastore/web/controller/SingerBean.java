package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SingerBean extends AbstractBean<Singer> {

    private static final long serialVersionUID = 9125920186450221214L;

    @EJB
    private SingerService service;

    @Override
    protected BaseService<Singer> getBaseService() {
        return service;
    }
}
