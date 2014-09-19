package com.nms.mediastore.ejb;

import com.nms.mediastore.service.TopicService;
import com.nms.mediastore.entity.Topic;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class TopicServiceBean extends AbstractService<Topic> implements TopicService {

    private static final long serialVersionUID = -3895208240070641207L;
    
    @PersistenceContext
    private EntityManager em;

    public TopicServiceBean() {
        super(Topic.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<Topic> root, CriteriaBuilder cb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
