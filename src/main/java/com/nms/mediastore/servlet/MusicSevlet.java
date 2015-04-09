package com.nms.mediastore.servlet;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.entity.Music;
import com.nms.mediastore.entity.MusicLink;
import com.nms.mediastore.service.MusicLinkService;
import com.nms.mediastore.service.MusicService;
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

@WebServlet(name = "MusicSevlet", urlPatterns = {"/download/music"})
public class MusicSevlet extends HttpServlet {

    private static final long serialVersionUID = 3556806167911598854L;
    private static final Logger LOG = Logger.getLogger(MusicSevlet.class.getName());
    private static final String DEFAULT_ID = "default";

    @EJB
    private MusicLinkService musicLinkService;
    @EJB
    private MusicService musicService;

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
            MusicLink link;
            FileEntry musicFile = null;
            try {
                if (uuid.equals(DEFAULT_ID)) {
                    Music music = musicService.getDefault();
                    musicFile = music.getMusicFile();
                } else {
                    link = musicLinkService.findByUUID(uuid);
                    musicFile = link.getMusic().getMusicFile();
                }
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Music link not found or expirated uuid = {0} and ERROR: {1}", new Object[]{
                    uuid, e.toString()
                });
            }

            if (musicFile != null && Validator.isNotNull(musicFile.getUri())) {
                File in = new File(AppConfig.getFileStorePath() + musicFile.getUri());
                if (in.exists()) {
                    response.setContentType(musicFile.getContentType());
                    response.setContentLengthLong(musicFile.getSize());
                    response.setHeader("Content-Disposition", "attachment;filename=\"" + musicFile.getName() + "\"");
                    try (OutputStream out = response.getOutputStream()) {
                        FileUtils.copyFile(in, out);
                    }
                } else {
                    LOG.log(Level.WARNING, "Music file not found = {0}", new Object[]{
                        AppConfig.getFileStorePath() + musicFile.getUri()
                    });
                }
            } else {
                LOG.log(Level.WARNING, "Music file not found = {0}", new Object[]{
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
        return "NMS Download Music Service";
    }

}
