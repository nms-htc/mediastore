package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.Group;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.UserService;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.util.MessageUtil;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserBean extends AbstractBean<User> {

    private static final long serialVersionUID = -1150645605262032055L;

    private SelectItem[] groupSelectItems;

    @EJB
    private UserService service;

    public UserBean() {
    }

    @Override
    protected BaseService<User> getBaseService() {
        return service;
    }

    @Override
    protected User initEntity() {
        return new User();
    }
    
    public void prepareChangePassword(User user) {
        current = user;
    }
    
    public void changePassword() {
        try {
            service.updateUserPassword(current);
            MessageUtil.addGlobalInfoMessage("change-password-successfull");
        } catch (Exception e) {
            MessageUtil.addGlobalErrorMessage("change-password-error", e);
        }
    }

    public SelectItem[] getGroupSelectItems() {
        if (groupSelectItems == null) {
            groupSelectItems = new SelectItem[Group.values().length];
            for (int i = 0; i < Group.values().length; i++) {
                groupSelectItems[i] = new SelectItem(Group.values()[i],
                        MessageUtil.getBundleMessage(Group.values()[i].name()));
            }
        }
        return groupSelectItems;
    }

    public void setGroupSelectItems(SelectItem[] groupSelectItems) {
        this.groupSelectItems = groupSelectItems;
    }
}
