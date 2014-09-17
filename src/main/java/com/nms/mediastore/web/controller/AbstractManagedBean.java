package com.nms.mediastore.web.controller;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.util.MessageUtil;
import java.io.Serializable;
import org.primefaces.model.LazyDataModel;

/**
 * Abtrast class that implement all basic functionalities managed bean for
 * entities.
 *
 * @author Nguyen Trong Cuong (cuongnt1987@gmail.com)
 * @since 16/09/2014
 * @version 1.0
 * @param <T> Entity Class Type
 * @param <Id> Entity's Id Class Type
 */
public abstract class AbstractManagedBean<T, Id> implements Serializable {

    private static final long serialVersionUID = 8024568564171342875L;
    private static final String REQUEST_SUCCESS_MESSAGE = "your-request-has-been-successfully-implemented";
    private static final String REQUEST_FAIL_MESSAGE = "your-request-fails";

    protected T current;
    protected LazyDataModel<T> model;

    public AbstractManagedBean() {
    }

    /**
     * Call back method persist action
     */
    protected void onBeforePersist() {
    }

    /**
     * Call back method persist action
     */
    protected void onAfterPersist() {
    }

    /**
     * Call back method persist action
     */
    protected void onPersistSuccess() {
        MessageUtil.addGlobalInfoMessage(REQUEST_SUCCESS_MESSAGE);
    }

    /**
     * Call back method persist action
     */
    protected void onPersistError(Throwable t) {
        MessageUtil.addGlobalErrorMessage(REQUEST_FAIL_MESSAGE, t);
    }

    /**
     * Persist entity to db
     */
    public void persist() {
        onBeforePersist();
        try {
            getBaseService().persist(current);
            onPersistSuccess();
        } catch (Exception e) {
            onPersistError(e);
        }
        onAfterPersist();
    }

    /**
     * Call back method update action
     */
    protected void onBeforeUpdate() {
    }

    /**
     * Call back method update action
     */
    protected void onAfterUpdate() {
    }

    /**
     * Call back method update action
     */
    protected void onSuccessUpdate() {
        MessageUtil.addGlobalInfoMessage(REQUEST_SUCCESS_MESSAGE);
    }

    /**
     * Call back method update action
     */
    protected void onErrorUpdate(Throwable t) {
        MessageUtil.addGlobalErrorMessage(REQUEST_FAIL_MESSAGE, t);
    }

    /**
     * Update entity and save to db
     */
    public void update() {
        onBeforeUpdate();
        try {
            getBaseService().update(current);
            onSuccessUpdate();
        } catch (Exception e) {
            onErrorUpdate(e);
        }
        onAfterUpdate();
    }

    /**
     * Call back method remove action
     */
    protected void onBeforeRemove(T entity) {
    }

    /**
     * Call back method remove action
     */
    protected void onAfterRemove(T entity) {
    }

    /**
     * Call back method remove action
     */
    protected void onSuccessRemove(T entity) {
        MessageUtil.addGlobalInfoMessage(REQUEST_SUCCESS_MESSAGE);
    }

    /**
     * Call back method remove action
     */
    protected void onErrorRemove(T entity, Throwable t) {
        MessageUtil.addGlobalErrorMessage(REQUEST_FAIL_MESSAGE, t);
    }

    /**
     * Remove entity from db
     *
     * @param entity entity instance for removing
     */
    public void remove(T entity) {
        onBeforeRemove(entity);
        try {
            getBaseService().remove(entity);
            onSuccessRemove(entity);
        } catch (Exception e) {
            onErrorRemove(entity, e);
        }
        onAfterRemove(entity);
    }

    /**
     * Initilize a new instance of the entity;
     *
     * @return new instanse of entity
     */
    protected abstract T initEntity();

    /**
     * Factory method for initilize a new instance of LazyDataModel (Primefaces)
     *
     * @return new instance of LazyDataModel for the entity.
     */
    protected abstract LazyDataModel<T> initDataModel();

    /**
     * Factoty method for BasicService EJB
     *
     * @return BasicSerivce instanse
     */
    protected abstract BaseService<T, Id> getBaseService();

    /* getters and setters */
    public T getCurrent() {

        if (current == null) {
            current = initEntity();
        }

        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public LazyDataModel<T> getModel() {
        if (model == null) {
            model = initDataModel();
        }
        return model;
    }

    public void setModel(LazyDataModel<T> model) {
        this.model = model;
    }
}
