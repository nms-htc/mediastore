package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.ThumbnailEntity;
import com.nms.mediastore.util.MessageUtil;
import java.io.IOException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public abstract class AbstractThumbnailBean<T extends ThumbnailEntity> extends AbstractBean<T> {

    private static final long serialVersionUID = -3134825097930589072L;

    /* upload thumbnail listener */
    public void thumbnailUploadListener(FileUploadEvent event) {
        try {
            current.getThumbnail().setContentType(event.getFile().getContentType());
            current.getThumbnail().setName(event.getFile().getFileName());
            current.getThumbnail().setSize(event.getFile().getSize());
            current.getThumbnail().setInputStream(event.getFile().getInputstream());
            MessageUtil.addGlobalInfoMessage("image-has-been-uploaded");
        } catch (IOException ex) {
            MessageUtil.addGlobalErrorMessage("upload-error", ex);
        }
    }
}
