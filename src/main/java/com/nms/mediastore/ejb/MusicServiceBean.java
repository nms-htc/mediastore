package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.service.MusicService;
import com.nms.mediastore.entity.Music;
import com.nms.mediastore.entity.Music_;
import java.util.logging.Logger;
import com.nms.mediastore.util.AppConfig;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

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

    @Override
    public List<Music> getHotMusic(int count) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Music> cq = cb.createQuery(Music.class);
        Root<Music> root = cq.from(Music.class);
        cq.select(root);
        
        cq.orderBy(new Order[] {
            cb.desc(root.get(Music_.hot)),
            cb.desc(root.get(Music_.modifiedDate))
        });
        
        TypedQuery<Music> q = em.createQuery(cq);
        q.setMaxResults(count);
        return q.getResultList();
    }
    
}
