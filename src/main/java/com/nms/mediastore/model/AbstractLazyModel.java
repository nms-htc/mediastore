/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.model;

import com.nms.mediastore.ejb.BaseService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author CuongNT
 * @param <T>
 * @param <Id>
 */
public abstract class AbstractLazyModel<T, Id> extends LazyDataModel<T> {

    private static final long serialVersionUID = -1137464869996262401L;

    protected abstract BaseService<T, Id> getBaseService();

    protected abstract Id parserRowKey(String rowKey);

    @Override
    public T getRowData(String rowKey) {
        return getBaseService().find(parserRowKey(rowKey));
    }

    @Override
    public abstract Object getRowKey(T object);

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.setRowCount(getBaseService().countForPFDatatable(filters));
        return getBaseService().searchForPFDatatable(first, first, sortField, sortOrder, filters);
    }
}
