/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.servlet;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.entity.ThumbnailEntity;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.util.AppConfig;
import com.nms.mediastore.util.Validator;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Cuong
 * @param <T>
 */
public class ImageDownloadTask<T extends ThumbnailEntity> implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ImageDownloadTask.class.getName());

    private final BaseService<T> service;
    private final Long id;
    private final HttpServletResponse response;
    private final AsyncContext asyncContext;
    private final ServletContext servletContext;

    public ImageDownloadTask(BaseService<T> service, Long id, AsyncContext asyncContext) {
        this.service = service;
        this.id = id;
        this.response = (HttpServletResponse) asyncContext.getResponse();
        this.asyncContext = asyncContext;
        servletContext = asyncContext.getRequest().getServletContext();
    }

    @Override
    public void run() {
        ThumbnailEntity entity = service.find(id);
        FileEntry file = entity.getThumbnail();
        File in;
        if (Validator.isNotNull(file.getUri()) && Validator.isNotNull(file.getName())) {
            response.setContentType(file.getContentType());
            response.setContentLengthLong(file.getSize());
            response.setHeader("Content-Disposition", "inline;filename=\"" + file.getName() + "\"");
            String path = AppConfig.getFileStorePath() + file.getUri();
            in = new File(path);
        } else {
            in = new File(servletContext.getRealPath("/resources/image/male-faces.png"));
        }
        
        try (OutputStream out = response.getOutputStream()) {
            FileUtils.copyFile(in, out);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "cannot download thumbnail image", ex);
        }

        asyncContext.complete();
    }

}
