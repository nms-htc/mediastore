package com.nms.mediastore.servlet;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.service.MusicService;
import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.service.TopicService;
import com.nms.mediastore.service.UserService;
import com.nms.mediastore.util.Validator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ImageSevlet", urlPatterns = {"/download/img"}, asyncSupported = true)
public class ImageSevlet extends HttpServlet {

    private static final long serialVersionUID = 1988135275957211095L;
    private static final Logger LOGGER = Logger.getLogger(ImageSevlet.class.getName());
    private static final String TYPE = "type";
    private static final String ID = "id";
    private static final String TOPIC_TYPE = "topic";
    private static final String ARTIST_TYPE = "artist";
    private static final String USER_TYPE = "user";
    private static final String SINGER_TYPE = "singer";
    private static final String MUSIC_TYPE = "music";

    @EJB
    private UserService userService;
    @EJB
    private ArtistService artistService;
    @EJB
    private TopicService topicService;
    @EJB
    private SingerService singerService;
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

        final String type = request.getParameter(TYPE);
        final String idStr = request.getParameter(ID);

        // validate parameters
        if (Validator.isNull(type) || Validator.isNull(idStr)) {
            return;
        }
        // parser
        final Long id = Long.parseLong(idStr);
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AsyncListener() {

            @Override
            public void onComplete(AsyncEvent event) throws IOException {
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                LOGGER.log(Level.SEVERE, "download thumbnail image file timeout");
                asyncContext.complete();
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                LOGGER.log(Level.SEVERE, "download thumbnail image file error", event.getThrowable());
                asyncContext.complete();
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
            }
        });
        
        switch (type) {
            case MUSIC_TYPE:
                asyncContext.start(new ImageDownloadTask<>(musicService, id, asyncContext));
                break;
            case USER_TYPE:
                asyncContext.start(new ImageDownloadTask<>(userService, id, asyncContext));
                break;
            case TOPIC_TYPE:
                asyncContext.start(new ImageDownloadTask<>(topicService, id, asyncContext));
                break;
            case SINGER_TYPE:
                asyncContext.start(new ImageDownloadTask<>(singerService, id, asyncContext));
                break;
            case ARTIST_TYPE:
                asyncContext.start(new ImageDownloadTask(artistService, id, asyncContext));
                break;
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
    }
}
