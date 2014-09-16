package com.nms.mediastore.ejb.impl;

import com.nms.mediastore.ejb.MusicFacadeLocalBean;
import com.nms.mediastore.entity.Music;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

@Stateless
public class MusicFacadeBeanImpl extends AbstractServiceBean<Music, Long> implements MusicFacadeLocalBean {

    private static final long serialVersionUID = -383721771132454218L;

    @PersistenceContext
    private EntityManager em;

    public MusicFacadeBeanImpl() {
        super(Music.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected List<Predicate> buildCondition(Map<String, Object> filters, Root<Music> root, CriteriaBuilder cb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Order buildOrder(String sortField, SortOrder sortOrder, CriteriaBuilder cb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
