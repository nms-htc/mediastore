package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.service.TopicService;
import com.nms.mediastore.entity.Topic;
import com.nms.mediastore.util.AppConfig;
import com.nms.mediastore.util.Validator;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

@Stateless
public class TopicServiceBean extends AbstractThumbnailService<Topic> implements TopicService {

    private static final long serialVersionUID = -3895208240070641207L;
    private static final Logger LOGGER = Logger.getLogger(TopicService.class.getName());

    public TopicServiceBean() {
        super(Topic.class);
    }

    @Override
    protected void onAfterPersist(Topic entity) {
        super.onBeforePersist(entity);
        FileEntry thumbnail = entity.getThumbnail();
        if (thumbnail != null && Validator.isNotNull(thumbnail.getName()) && thumbnail.getInputStream() != null) {
            try {
                String uri = AppConfig.getFileUri(entity.getId(), AppConfig.TOPIC_FOLDER, thumbnail.getName());
                thumbnail.setUri(uri);
                File out = new File(AppConfig.getFileStorePath() + uri);
                FileUtils.copyInputStreamToFile(thumbnail.getInputStream(), out);
                em.merge(entity);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Error saving thumbnail", ex);
                throw new EJBException("file-can-not-store");
            }
        }
    }

    @Override
    protected String getFolderName() {
        return AppConfig.TOPIC_FOLDER;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
