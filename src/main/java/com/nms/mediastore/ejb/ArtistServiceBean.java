package com.nms.mediastore.ejb;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.entity.Artist;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class ArtistServiceBean extends AbstractService<Artist> implements ArtistService {

    private static final long serialVersionUID = 3659025770804313715L;
    
    @PersistenceContext
    private EntityManager em;

    public ArtistServiceBean() {
        super(Artist.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
