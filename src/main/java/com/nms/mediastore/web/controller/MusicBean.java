package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.MusicService;
import com.nms.mediastore.entity.Music;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.util.MessageUtil;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class MusicBean extends AbstractThumbnailBean<Music> {

    private static final long serialVersionUID = -8912312157585232815L;

    @EJB
    private MusicService service;

    public MusicBean() {
    }

    @Override
    protected BaseService<Music> getBaseService() {
        return service;
    }

    @Override
    protected Music initEntity() {
        return new Music();
    }
    
    /* upload thumbnail listener */
    public void musicUploadListener(FileUploadEvent event) {
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
