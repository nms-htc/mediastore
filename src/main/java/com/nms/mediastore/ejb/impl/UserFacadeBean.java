package com.nms.mediastore.ejb.impl;

import com.nms.mediastore.ejb.UserFacadeLocalBean;
import com.nms.mediastore.entity.Group;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.entity.User_;
import com.nms.mediastore.util.Validator;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.DatatypeConverter;
import org.primefaces.model.SortOrder;

@Stateless
public class UserFacadeBean extends AbstractServiceBean<User, Long> implements UserFacadeLocalBean {

    private static final long serialVersionUID = 459455862821896874L;

    @PersistenceContext
    private EntityManager em;

    public UserFacadeBean() {
        super(User.class);
    }

    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public User findByUP(String username, String password) {
        try {
            password = hashSHA256(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EJBException("can-not-hash(sha256)-password", e);
        }
        TypedQuery<User> q = em.createNamedQuery("User.findByUP", User.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        return q.getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> q = em.createNamedQuery("User.findByU", User.class);
        q.setParameter("username", username);
        return q.getSingleResult();
    }

    @Override
    protected void onBeforePersist(User entity) {
        try {
            entity.setPassword(hashSHA256(entity.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EJBException("can-not-hash(sha256)-password", e);
        }
    }

    @Override
    protected List<Predicate> buildCondition(Map<String, Object> filters, Root<User> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
//            switch (entry.getKey()) {
//                //case User_.email.getName():
//                    
//            }
        }

        return predicates;
    }

    @Override
    protected Order buildOrder(String sortField, SortOrder sortOrder, CriteriaBuilder cb) {
        return null;
    }

    protected String hashSHA256(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(value.getBytes("UTF-8"));
        byte[] hashed = md.digest();
        return DatatypeConverter.printBase64Binary(hashed);
    }

    @Override
    public User updateUserPassword(User user, String oldPassword, String newPassword) {

        if (Validator.isNull(oldPassword)) {
            throw new EJBException("old-password-can-not-be-null");
        }

        if (Validator.isNull(newPassword)) {
            throw new EJBException("new-password-can-not-be-null");
        }

        String oldPasswordHashed = null;
        String newPasswordHashed = null;
        try {
            oldPasswordHashed = hashSHA256(oldPassword);
            newPasswordHashed = hashSHA256(newPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new EJBException("can-not-hash-password", ex);
        }

        if (user.getPassword().compareTo(oldPasswordHashed) == 0) {
            user.setPassword(newPasswordHashed);
            em.merge(user);
        } else {
            throw new EJBException("password-not-match");
        }

        return user;
    }

    @Override
    public List<User> findAdministrators() {
        List<User> admins = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            cq.where(cb.isMember(Group.Administrator, root.get(User_.groups)));

            TypedQuery<User> q = em.createQuery(cq);
            admins = q.getResultList();
        } catch (Exception e) {

        }

        return admins;
    }
}
