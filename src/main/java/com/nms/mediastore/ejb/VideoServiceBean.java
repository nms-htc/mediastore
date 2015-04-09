/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.BaseEntity_;
import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.entity.Music;
import com.nms.mediastore.entity.Music_;
import com.nms.mediastore.entity.ThumbnailEntity_;
import com.nms.mediastore.entity.Video;
import com.nms.mediastore.entity.Video_;
import com.nms.mediastore.service.VideoService;
import com.nms.mediastore.util.AppConfig;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 *
 * @author richard
 */
@Stateless
public class VideoServiceBean extends AbstractThumbnailService<Video> implements VideoService {

    private static final long serialVersionUID = -810326774689939585L;
    private static final Logger logger = Logger.getLogger(VideoServiceBean.class.getName());

    public VideoServiceBean() {
        super(Video.class);
    }

    @Override
    protected String getFolderName() {
        return AppConfig.VIDEO_FOLDER;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
    
    @Override
    protected void onAfterPersist(Video entity) {
        super.onBeforePersist(entity);
        // save thumbnail file.
        FileEntry thumbnail = entity.getThumbnail();
        FileEntry musicFile = entity.getVideoFile();
        saveFile(entity.getId(), thumbnail, false);
        saveFile(entity.getId(), musicFile, false);
        // update entity
        em.merge(entity);
    }

    @Override
    protected void onAfterUpdate(Video entity) {
        super.onAfterUpdate(entity);
        // save thumbnail file.
        FileEntry thumbnail = entity.getThumbnail();
        FileEntry musicFile = entity.getVideoFile();
        saveFile(entity.getId(), thumbnail, true);
        saveFile(entity.getId(), musicFile, true);
        // update entity
        em.merge(entity);
    }

    @Override
    public List<Video> getHotVideo(int count) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Video> cq = cb.createQuery(Video.class);
        Root<Video> root = cq.from(Video.class);
        cq.select(root);
        
        cq.where(cb.lessThanOrEqualTo(root.get(Video_.publishDate), new Date()));

        cq.orderBy(new Order[]{
            cb.desc(root.get(Video_.hot)),
            cb.desc(root.get(BaseEntity_.modifiedDate))
        });

        TypedQuery<Video> q = em.createQuery(cq);
        q.setMaxResults(count);
        return q.getResultList();
    }

    @Override
    public void setDefault(Video video) {
         // reset all video
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Video> cu = em.getCriteriaBuilder().createCriteriaUpdate(Video.class);
        Root<Video> root = cu.from(Video.class);
        cu.set(root.get(Video_.defaultVideo), false);
        em.createQuery(cu).executeUpdate();
        em.flush();
        // set current music to default
        video.setDefaultVideo(Boolean.TRUE);
        em.merge(video);
    }

    @Override
    public Video getDefault() {
        Video music = null;
        try {
            TypedQuery<Video> q = em.createNamedQuery("Video.getDefault", Video.class);
            q.setMaxResults(1);
            music = q.getSingleResult();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Cannot get default videoERROR: {0}", e.toString());
        }
        
        return music;
    }

}
