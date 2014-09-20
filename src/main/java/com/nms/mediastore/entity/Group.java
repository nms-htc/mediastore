package com.nms.mediastore.entity;

import com.nms.mediastore.util.MessageUtil;

public enum Group {
    Administrator, Cp;

    @Override
    public String toString() {
        return MessageUtil.getBundleMessage(this.name());
    }
}
