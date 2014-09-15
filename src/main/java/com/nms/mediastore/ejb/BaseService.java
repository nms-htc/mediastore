/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author CuongNT
 * @param <T>
 * @param <Id>
 */
public interface BaseService<T, Id> extends Serializable {
    public T find(Id id);
    public List<T> findAll();
    public int countAll();
    public T persist(T entity);
    public T update(T entity);
    public void remove(T entity);
    public List<T> searchForPFDatatable(int start, int range, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    public int countForPFDatatable(Map<String, Object> filters);
}
