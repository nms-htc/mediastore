package com.nms.mediastore.model;

import com.nms.mediastore.ejb.BaseService;
import com.nms.mediastore.entity.User;

public class UserLazyDataModel extends AbstractLazyDataModel<User, Long> {

    private static final long serialVersionUID = 4432754631408591907L;

    public UserLazyDataModel(BaseService<User, Long> baseService) {
        super(baseService);
    }

    @Override
    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public Object getRowKey(User object) {
        return object.getUserId();
    }
}
