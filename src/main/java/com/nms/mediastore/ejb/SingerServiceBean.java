package com.nms.mediastore.ejb;

import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;

@Stateless
public class SingerServiceBean extends AbstractService<Singer> implements SingerService {

    private static final long serialVersionUID = 3818354189108798555L;
    private static final Logger LOGGER = Logger.getLogger(SingerService.class.getName());

    @PersistenceContext
    private EntityManager em;

    public SingerServiceBean() {
        super(Singer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void onBeforePersist(Singer entity) {
        super.onBeforePersist(entity);

        if (entity.getThumbnailPart() != null) {
            Part file = entity.getThumbnailPart();
            LOGGER.info("[SingerService] part.getName()" + file.getName());
            LOGGER.info("[SingerService] part.getSubmittedFileName()" + file.getSubmittedFileName());
            LOGGER.info("[SingerService] part.getSize()" + file.getSize());
            LOGGER.info("[SingerService] part.getSize()" + file.getContentType());
        }
    }

}
