package com.nms.mediastore.ejb;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.entity.Artist;
import com.nms.mediastore.util.AppConfig;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class ArtistServiceBean extends AbstractThumbnailService<Artist> implements ArtistService {

    private static final long serialVersionUID = 3659025770804313715L;
    private static final Logger LOGGER = Logger.getLogger(ArtistService.class.getName());

    public ArtistServiceBean() {
        super(Artist.class);
    }

    @Override
    protected String getFolderName() {
        return AppConfig.ARTIST_FOLDER;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
