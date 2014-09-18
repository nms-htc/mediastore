package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.UserService;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.model.UserLazyDataModel;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@SessionScoped
public class UserBean extends AbstractBean<User, Long> {

    private static final long serialVersionUID = -1150645605262032055L;
    
    @EJB
    private UserService service;

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
    
    public String getNumberAdmin() {
        return String.valueOf(service.findAdministrators());
    }
}
