/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.model;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.entity.User;

/**
 *
 * @author CuongNT
 */
public class UserLazyModel extends AbstractLazyModel<User, Long> {

    private static final long serialVersionUID = 4432754631408591907L;
    private final BaseService<User, Long> baseService;

    public UserLazyModel(BaseService<User, Long> baseService) {
        this.baseService = baseService;
    }
    
    @Override
    protected BaseService<User, Long> getBaseService() {
        return baseService;
    }

    @Override
    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public Object getRowKey(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
