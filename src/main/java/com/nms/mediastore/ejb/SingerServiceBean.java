package com.nms.mediastore.ejb;

import com.nms.mediastore.service.SingerService;
import com.nms.mediastore.entity.Singer;
import com.nms.mediastore.util.AppConfig;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class SingerServiceBean extends AbstractThumbnailService<Singer> implements SingerService {

    private static final long serialVersionUID = 3818354189108798555L;
    private static final Logger LOGGER = Logger.getLogger(SingerService.class.getName());

    public SingerServiceBean() {
        super(Singer.class);
    }

    @Override
    protected String getFolderName() {
        return AppConfig.SINGER_FOLDER;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
