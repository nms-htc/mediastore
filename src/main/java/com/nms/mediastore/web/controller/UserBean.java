package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.Group;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.UserService;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.util.MessageUtil;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
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
