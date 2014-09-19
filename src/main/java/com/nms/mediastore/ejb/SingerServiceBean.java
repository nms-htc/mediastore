package com.nms.mediastore.ejb;

import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class SingerServiceBean extends AbstractService<Singer> implements SingerService {

    private static final long serialVersionUID = 3818354189108798555L;

    @PersistenceContext
    private EntityManager em;

    public SingerServiceBean() {
        super(Singer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<Singer> root, CriteriaBuilder cb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
