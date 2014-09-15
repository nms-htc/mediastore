/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb.impl;

import com.nms.mediastore.ejb.AbstractServiceBean;
import com.nms.mediastore.ejb.UserFacadeLocalBean;
import com.nms.mediastore.entity.User;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.SortOrder;

/**
 *
 * @author CuongNT
 */
@Stateless
public class UserFacadeBeanImpl extends AbstractServiceBean<User, Long> implements UserFacadeLocalBean {
    
    private static final long serialVersionUID = 459455862821896874L;
    
    @PersistenceContext
    private EntityManager em;

    public UserFacadeBeanImpl() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<User> searchForPFDatatable(int start, int range, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countForPFDatatable(Map<String, Object> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
