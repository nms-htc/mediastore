package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class SingerBean extends AbstractBean<Singer> {

    private static final long serialVersionUID = 9125920186450221214L;
    
    private FileUpload fileUpload;
    
    public void fileUploadListener(FileUploadEvent event) {
        // event.getFile().get
    }

    @EJB
    private SingerService service;

    @Override
    protected BaseService<Singer> getBaseService() {
        return service;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }
}
