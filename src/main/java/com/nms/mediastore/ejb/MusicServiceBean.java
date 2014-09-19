package com.nms.mediastore.ejb;

import com.nms.mediastore.service.MusicService;
import com.nms.mediastore.entity.Music;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class MusicServiceBean extends AbstractService<Music> implements MusicService {

    private static final long serialVersionUID = -383721771132454218L;

    @PersistenceContext
    private EntityManager em;

    public MusicServiceBean() {
        super(Music.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<Music> root, CriteriaBuilder cb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
