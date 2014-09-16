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
public abstract class AbstractLazyDataModel<T, Id> extends LazyDataModel<T> {

    private static final long serialVersionUID = -1137464869996262401L;

    protected final BaseService<T, Id> baseService;

    public AbstractLazyDataModel(BaseService<T, Id> baseService) {
        this.baseService = baseService;
    }

    protected abstract Id parserRowKey(String rowKey);

    @Override
    public T getRowData(String rowKey) {
        return baseService.find(parserRowKey(rowKey));
    }

    @Override
    public abstract Object getRowKey(T object);

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.setRowCount(baseService.countForPFDatatable(filters));
        return baseService.searchForPFDatatable(first, first, sortField, sortOrder, filters);
    }
}
