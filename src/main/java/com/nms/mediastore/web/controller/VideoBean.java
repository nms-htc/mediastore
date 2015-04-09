/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.entity.Video;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.VideoService;
import com.nms.mediastore.util.MessageUtil;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author richard
 */
@Named
@ViewScoped
public class VideoBean extends AbstractThumbnailBean<Video> {

    private static final long serialVersionUID = -1935254668556941097L;
    
    @EJB
    private VideoService videoService;
    @Inject
    private User currentUser;

    @Override
    protected Video initEntity() {
        return new Video();
    }

    @Override
    protected BaseService<Video> getBaseService() {
        return videoService;
    }
    
    /* upload thumbnail listener */
    public void videoUploadListener(FileUploadEvent event) {
        try {
            FileEntry file = current.getVideoFile();
            if (file == null) {
                file = new FileEntry();
                current.setVideoFile(file);
            }
            file.setContentType(event.getFile().getContentType());
            file.setName(event.getFile().getFileName());
            file.setSize(event.getFile().getSize());
            file.setInputStream(event.getFile().getInputstream());
            MessageUtil.addGlobalInfoMessage("image-has-been-uploaded");
        } catch (IOException ex) {
            MessageUtil.addGlobalErrorMessage("upload-error", ex);
        }
    }

    @Override
    protected void onBeforePersist() {
        super.onBeforePersist();
        current.setUser(currentUser);
        if (current.getVideoFile()== null) {
            FacesContext.getCurrentInstance().validationFailed();
            throw new RuntimeException("video-file-is-required");
        }
    }
    
    public void setDefault(Video video) {
        try {
            videoService.setDefault(video);
            MessageUtil.addGlobalInfoMessage("video-action-set-default-success");
        } catch (Exception e) {
            MessageUtil.addGlobalErrorMessage("video-action-set-default-error", e);
        }
    }
}
