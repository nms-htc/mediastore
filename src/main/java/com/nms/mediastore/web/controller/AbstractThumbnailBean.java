package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.entity.ThumbnailEntity;
import com.nms.mediastore.util.MessageUtil;
import java.io.IOException;
import org.primefaces.event.FileUploadEvent;

public abstract class AbstractThumbnailBean<T extends ThumbnailEntity> extends AbstractBean<T> {

    private static final long serialVersionUID = -3134825097930589072L;

    /* upload thumbnail listener */
    public void thumbnailUploadListener(FileUploadEvent event) {
        try {
            FileEntry file = current.getThumbnail();
            file.setContentType(event.getFile().getContentType());
            file.setName(event.getFile().getFileName());
            file.setSize(event.getFile().getSize());
            file.setInputStream(event.getFile().getInputstream());
            MessageUtil.addGlobalInfoMessage("image-has-been-uploaded");
        } catch (IOException ex) {
            MessageUtil.addGlobalErrorMessage("upload-error", ex);
        }
    }
}
