package com.nms.mediastore.web.convertor;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.util.JsfUtil;
import java.lang.reflect.ParameterizedType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

abstract class AbstractEntityConvertor<T, Id> implements Converter {

    /* Factory method */
    protected abstract BaseService<T, Id> getBaseService();

    protected abstract Id getKey(String keyStr);

    protected abstract String getStringKey(T entity);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        if (JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }

        return getBaseService().find(getKey(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        if (value.getClass().equals(getEntityClass())) {
            T entity = (T) value;
            return getStringKey(entity);
        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass()
                    .getName() + "; expected type: " + getEntityClass().getName());
        }
    }

    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
