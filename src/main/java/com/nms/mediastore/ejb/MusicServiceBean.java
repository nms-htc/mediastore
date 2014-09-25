package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.service.MusicService;
import com.nms.mediastore.entity.Music;
import java.util.logging.Logger;
import com.nms.mediastore.util.AppConfig;
import javax.ejb.Stateless;

@Stateless
public class MusicServiceBean extends AbstractThumbnailService<Music> implements MusicService {

    private static final long serialVersionUID = -383721771132454218L;
    private static final Logger LOGGER = Logger.getLogger(MusicService.class.getName());

    public MusicServiceBean() {
        super(Music.class);
    }

    @Override
    protected String getFolderName() {
        return AppConfig.MUSIC_FOLDER;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    @Override
    protected void onAfterPersist(Music entity) {
        super.onBeforePersist(entity);
        // save thumbnail file.
        FileEntry thumbnail = entity.getThumbnail();
        FileEntry musicFile = entity.getMusicFile();
        saveFile(entity.getId(), thumbnail, false);
        saveFile(entity.getId(), musicFile, false);
        // update entity
        em.merge(entity);
    }

    @Override
    protected void onAfterUpdate(Music entity) {
        super.onAfterUpdate(entity);
        // save thumbnail file.
        FileEntry thumbnail = entity.getThumbnail();
        FileEntry musicFile = entity.getMusicFile();
        saveFile(entity.getId(), thumbnail, true);
        saveFile(entity.getId(), musicFile, true);
        // update entity
        em.merge(entity);
    }
    
}
