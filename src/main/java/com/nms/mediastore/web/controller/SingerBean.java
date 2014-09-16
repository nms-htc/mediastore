package com.nms.mediastore.web.controller;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.ejb.SingerFacadeLocalBean;
import com.nms.mediastore.entity.Singer;
import com.nms.mediastore.model.SingerLazyDataModel;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@SessionScoped
public class SingerBean extends AbstractManagedBean<Singer, Long> {

    private static final long serialVersionUID = 9125920186450221214L;

    @EJB
    private SingerFacadeLocalBean service;

    @Override
    protected Singer initEntity() {
        return new Singer();
    }

    @Override
    protected LazyDataModel<Singer> initDataModel() {
        return new SingerLazyDataModel(service);
    }

    @Override
    protected BaseService<Singer, Long> getBaseService() {
        return service;
    }

}
