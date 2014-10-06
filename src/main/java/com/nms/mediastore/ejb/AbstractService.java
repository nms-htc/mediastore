package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.BaseEntity;
import com.nms.mediastore.entity.BaseEntity_;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.util.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 * Generic implement basic functionalities of ejb beans.
 *
 * @author Nguyen Trong Cuong (cuongnt1987@gmail.com)
 * @since 16/09/2014
 * @version 1.0
 * @param <T> Entity Class Type
 */
public abstract class AbstractService<T extends BaseEntity> implements BaseService<T> {

    private static final long serialVersionUID = 8144780304797677034L;
    private static final Logger LOGGER = Logger.getLogger(BaseService.class.getName());

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(Long id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public int countAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    /* Callback method */
    protected void onBeforePersist(T entity) {
    }

    /* Callback method */
    protected void onAfterPersist(T entity) {
        BaseEntity baseEntity = (BaseEntity) entity;
        LOGGER.log(Level.INFO, "[BaseService] has persisted entity with ID = {0}", baseEntity.getId());
    }

    @Override
    public T persist(T entity) {
        onBeforePersist(entity);
        em.persist(entity);
        em.flush();
        onAfterPersist(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeUpdate(T entity) {
    }

    /* Callback method */
    protected void onAfterUpdate(T entity) {
    }

    @Override
    public T update(T entity) {
        onBeforeUpdate(entity);
        em.merge(entity);
        onAfterUpdate(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeRemove(T entity) {
    }

    /* Callback method */
    protected void onAfterRemove(T entity) {
    }

    @Override
    public void remove(T entity) {
        onBeforeRemove(entity);
        em.remove(em.merge(entity));
        onAfterRemove(entity);
    }

    @Override
    public int countForPFDatatable(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public List<T> searchForPFDatatable(int start, int range, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        Order[] orders = buildOrder(sortField, sortOrder, cb, root);

        if (orders != null && orders.length > 0) {
            cq.orderBy(orders);
        }

        TypedQuery<T> q = em.createQuery(cq);

        q.setFirstResult(start);
        q.setMaxResults(range);

        return q.getResultList();
    }

    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            Predicate predicate = buildCondition(entry, root, cb);
            if (predicate != null) {
                predicates.add(predicate);
            }
        }

        return predicates;
    }

    protected Predicate buildCondition(Map.Entry<String, Object> entry, Root<T> root, CriteriaBuilder cb) {
        return cb.equal(root.get(entry.getKey()), entry.getValue());
    }

    protected Order[] buildOrder(String sortField, SortOrder sortOrder, CriteriaBuilder cb, Root<T> root) {
        List<Order> orders = new ArrayList<>();
        if (sortOrder != null && Validator.isNotNull(sortField)) {
            switch (sortOrder) {
                case ASCENDING:
                    orders.add(cb.asc(root.get(sortField)));
                    break;
                case DESCENDING:
                    orders.add(cb.desc(root.get(sortField)));
                    break;
            }
        } else {
            orders.add(cb.desc(root.get(BaseEntity_.modifiedDate)));
        }

        return orders.toArray(new Order[]{});
    }
}
