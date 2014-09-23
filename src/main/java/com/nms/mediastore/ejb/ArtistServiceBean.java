package com.nms.mediastore.ejb;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.entity.Artist;
import com.nms.mediastore.entity.FileEntry;
import com.nms.mediastore.util.AppConfig;
import com.nms.mediastore.util.Validator;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.FileUtils;

@Stateless
public class ArtistServiceBean extends AbstractService<Artist> implements ArtistService {

    private static final long serialVersionUID = 3659025770804313715L;
    private static final Logger LOGGER = Logger.getLogger(ArtistService.class.getName());
    
    @PersistenceContext
    private EntityManager em;

    public ArtistServiceBean() {
        super(Artist.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    protected void onAfterPersist(Artist entity) {
        super.onBeforePersist(entity);
        FileEntry thumbnail = entity.getThumbnail();
        if (thumbnail != null && Validator.isNotNull(thumbnail.getName()) && thumbnail.getInputStream() != null) {
            try {
                String uri = AppConfig.getFileUri(entity.getId(), AppConfig.ARTIST_FOLDER, thumbnail.getName());
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
}
