package com.nms.mediastore.service;

import com.nms.mediastore.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 * Basic functionalities of ejb beans.
 *
 * @author Nguyen Trong Cuong (cuongnt1987@gmail.com)
 * @since 16/09/2014
 * @version 1.0
 * @param <T> Entity Class Type
 */
public interface BaseService<T extends BaseEntity> extends Serializable {

    public T find(Long id);

    public List<T> findAll();

    public int countAll();

    public T persist(T entity);

    public T update(T entity);

    public void remove(T entity);

    public List<T> searchForPFDatatable(int start, int range, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public int countForPFDatatable(Map<String, Object> filters);
}
