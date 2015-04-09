/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.servlet;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.entity.Music;
import com.nms.mediastore.entity.MusicLink;
import com.nms.mediastore.entity.Video;
import com.nms.mediastore.entity.VideoLink;
import com.nms.mediastore.service.VideoLinkService;
import com.nms.mediastore.service.VideoService;
import com.nms.mediastore.util.AppConfig;
import com.nms.mediastore.util.Validator;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author richard
 */
@WebServlet(name = "VideoServlet", urlPatterns = {"/download/video"})
public class VideoServlet extends HttpServlet {

    private static final long serialVersionUID = 2221805939439704672L;
    private static final Logger LOG = Logger.getLogger(VideoServlet.class.getName());
    private static final String DEFAULT_ID = "default";

    @EJB
    private VideoLinkService linkService;
    @EJB
    private VideoService videoService;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("id");
        if (Validator.isNotNull(uuid)) {
            VideoLink link;
            FileEntry videoFile = null;
            try {
                if (uuid.equals(DEFAULT_ID)) {
                    Video video = videoService.getDefault();
                    videoFile = video.getVideoFile();
                } else {
                    link = linkService.findByUUID(uuid);
                    videoFile = link.getVideo().getVideoFile();
                }
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Video link not found or expirated uuid = {0} and ERROR: {1}", new Object[]{
                    uuid, e.toString()
                });
            }

            if (videoFile != null && Validator.isNotNull(videoFile.getUri())) {
                File in = new File(AppConfig.getFileStorePath() + videoFile.getUri());
                if (in.exists()) {
                    response.setContentType(videoFile.getContentType());
                    response.setContentLengthLong(videoFile.getSize());
                    response.setHeader("Content-Disposition", "attachment;filename=\"" + videoFile.getName() + "\"");
                    try (OutputStream out = response.getOutputStream()) {
                        FileUtils.copyFile(in, out);
                    }
                } else {
                    LOG.log(Level.WARNING, "Video file not found = {0}", new Object[]{
                        AppConfig.getFileStorePath() + videoFile.getUri()
                    });
                }
            } else {
                LOG.log(Level.WARNING, "Video file not found = {0}", new Object[]{
                    uuid
                });
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
