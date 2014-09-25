package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.entity.ThumbnailEntity;
import com.nms.mediastore.util.AppConfig;
import java.io.File;
import com.nms.mediastore.util.Validator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public abstract class AbstractThumbnailService<T extends ThumbnailEntity> extends AbstractService<T> {

    private static final long serialVersionUID = 8895805077499564287L;

    public AbstractThumbnailService(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    protected void onAfterPersist(T entity) {
        super.onBeforePersist(entity);
        // save thumbnail file.
        FileEntry thumbnail = entity.getThumbnail();
        saveFile(entity.getId(), thumbnail, false);
        // update entity
        em.merge(entity);
    }

    @Override
    protected void onAfterUpdate(T entity) {
        super.onAfterUpdate(entity);
        // save thumbnail file.
        FileEntry thumbnail = entity.getThumbnail();
        saveFile(entity.getId(), thumbnail, true);
        // update entity
        em.merge(entity);
    }

    @Override
    protected void onAfterRemove(T entity) {
        super.onAfterRemove(entity);
        FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath()
                + getFolderName() + File.separator + entity.getId()));
    }

    protected void saveFile(Long id, FileEntry file, boolean deleteOld) {
        if (file != null && Validator.isNotNull(file.getName()) && file.getInputStream() != null
                && id != null) {
            try {
                String uri = AppConfig.getFileUri(id, getFolderName(), file.getName());

                if (deleteOld && Validator.isNotNull(file.getUri())
                        && FilenameUtils.normalize(uri).equals(FilenameUtils.normalize(file.getUri()))) {
                    // delete old file
                    FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + file.getUri()));
                }
                // add new file

                File out = new File(AppConfig.getFileStorePath() + uri);
                FileUtils.copyInputStreamToFile(file.getInputStream(), out);
                // save uri
                file.setUri(uri);
            } catch (IOException ex) {
                getLogger().log(Level.SEVERE, "Error when store file : {0}", ex.toString());
                throw new EJBException("file-can-not-store");
            }
        }
    }

    protected abstract String getFolderName();

    protected abstract Logger getLogger();
}
