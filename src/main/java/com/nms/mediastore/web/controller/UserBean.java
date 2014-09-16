package com.nms.mediastore.web.controller;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.ejb.UserFacadeLocalBean;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.model.UserLazyDataModel;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@SessionScoped
public class UserBean extends AbstractManagedBean<User, Long> {

    private static final long serialVersionUID = -1150645605262032055L;
    
    @EJB
    private UserFacadeLocalBean service;

    public UserBean() {
    }
    
    @Override
    protected User initEntity() {
        return new User();
    }

    @Override
    protected LazyDataModel<User> initDataModel() {
        return new UserLazyDataModel(service);
    }

    @Override
    protected BaseService<User, Long> getBaseService() {
        return service;
    }
}
