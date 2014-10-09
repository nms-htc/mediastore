package com.nms.mediastore.ws;

import com.nms.mediastore.entity.Music;
import com.nms.mediastore.entity.MusicLink;
import com.nms.mediastore.service.MusicLinkService;
import com.nms.mediastore.service.MusicService;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(endpointInterface = "com.nms.mediastore.ws.MsService", serviceName = "MediaService", portName = "MediaServicePort")
public class MsServiceImpl implements MsService {

    @Resource
    private WebServiceContext context;
    @EJB
    private MusicLinkService musicLinkService;
    @EJB
    private MusicService musicService;

    @Override
    public String[][] getHotMusicLink(int count, int expireTime, int timeUnit) throws Exception {
        if (expireTime <= 0) {
            expireTime = 1;
        }
        switch (timeUnit) {
            case Calendar.MINUTE:
                break;
            case Calendar.HOUR:
                break;
            case Calendar.DATE:
                break;
            case Calendar.WEEK_OF_MONTH:
                break;
            case Calendar.MONTH:
                break;
            default:
                timeUnit = Calendar.DATE;
                break;
        }
        String[][] links = null;
        if (count > 0) {
            ServletContext servletContext = (ServletContext) context
                    .getMessageContext().get(MessageContext.SERVLET_CONTEXT);
            List<Music> musics;

            try {
                musics = musicService.getHotMusic(count);
                links = new String[musics.size()][2];
            } catch (Exception ex) {
                throw new Exception("Error when get hot music with count = " + count, ex);
            }

            try {
                for (int i = 0; i < musics.size(); i++) {
                    Music music = musics.get(i);
                    MusicLink musicLink = new MusicLink();
                    musicLink.setMusic(music);
                    Calendar expireDate = Calendar.getInstance();
                    expireDate.add(timeUnit, expireTime);
                    musicLink.setExpireDate(expireDate.getTime());
                    musicLinkService.persist(musicLink);
                    String uuid = musicLink.getUuid();
                    links[i][0] = music.getTitle();
                    links[i][1] = servletContext.getContextPath() + "/download/music?id=" + uuid;
                }
            } catch (Exception e) {
                throw new Exception("Error when when create music link" + count, e);
            }

        }
        return links;
    }

    @Override
    public String[] getDefaultMusicLink() {
        String[] result = new String[2];
        ServletContext servletContext = (ServletContext) context
                    .getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        Music defaultMusic = musicService.getDefault();
        result[0] = defaultMusic.getTitle();
        result[1] = servletContext.getContextPath() + "/download/music?id=default";
        return result;
    }
}
