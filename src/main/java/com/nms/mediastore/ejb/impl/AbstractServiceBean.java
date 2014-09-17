package com.nms.mediastore.ejb.impl;

import com.nms.mediastore.ejb.BaseService;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
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
 * @param <Id> Entity's Id Class Type
 */
public abstract class AbstractServiceBean<T, Id> implements BaseService<T, Id> {

    private static final long serialVersionUID = 8144780304797677034L;

    protected abstract EntityManager getEntityManager();
    private final Class<T> entityClass;

    public AbstractServiceBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(Id id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    @Override
    public int countAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        return q.getSingleResult().intValue();
    }

    /* Callback method */
    protected void onBeforePersist(T entity) {
    }

    /* Callback method */
    protected void onAfterPersist(T entity) {
    }

    @Override
    public T persist(T entity) {
        onAfterPersist(entity);
        getEntityManager().persist(entity);
        getEntityManager().flush();
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
        getEntityManager().merge(entity);
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
        getEntityManager().remove(getEntityManager().merge(entity));
        onAfterRemove(entity);
    }

    @Override
    public int countForPFDatatable(Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));

        List<Predicate> predicates = buildCondition(filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public List<T> searchForPFDatatable(int start, int range, String sortField, 
            SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        
        List<Predicate> predicates = buildCondition(filters, root, cb);
        
        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }
        
        Order order = buildOrder(sortField, sortOrder, cb);
        
        if (order != null) {
            cq.orderBy(order);
        }
        
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        
        q.setFirstResult(start);
        q.setMaxResults(range);
        
        return q.getResultList();
    }

    protected abstract List<Predicate> buildCondition(Map<String, Object> filters, Root<T> root, CriteriaBuilder cb);
    
    protected abstract Order buildOrder(String sortField, SortOrder sortOrder, CriteriaBuilder cb);
}
