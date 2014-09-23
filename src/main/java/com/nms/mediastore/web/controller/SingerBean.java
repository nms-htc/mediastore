package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import com.nms.mediastore.util.MessageUtil;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class SingerBean extends AbstractBean<Singer> {

    private static final long serialVersionUID = 9125920186450221214L;
    
    private UploadedFile fileUpload;
    
    public void fileUploadListener(FileUploadEvent event) {
        try {
            current.getThumbnail().setContentType(event.getFile().getContentType());
            current.getThumbnail().setName(event.getFile().getFileName());
            current.getThumbnail().setSize(event.getFile().getSize());
            current.getThumbnail().setInputStream(event.getFile().getInputstream());
        } catch (IOException ex) {
            MessageUtil.addGlobalErrorMessage("upload-error", ex);
        }
                
    }

    @EJB
    private SingerService service;

    @Override
    protected BaseService<Singer> getBaseService() {
        return service;
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }
    
    @Override
    protected Singer initEntity() {
        return new Singer();
    }
}
