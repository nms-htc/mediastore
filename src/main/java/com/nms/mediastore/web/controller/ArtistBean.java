package com.nms.mediastore.web.controller;

import com.nms.mediastore.service.ArtistService;
import com.nms.mediastore.service.BaseService;
import com.nms.mediastore.entity.Artist;
import com.nms.mediastore.util.MessageUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class ArtistBean extends AbstractThumbnailBean<Artist> {

    private static final long serialVersionUID = -7081995377651933248L;
    private static final Logger LOG = Logger.getLogger(ArtistBean.class.getName());
    
    @NotNull
    protected String batchName;

    @EJB
    private ArtistService service;

    @Override
    protected BaseService<Artist> getBaseService() {
        return service;
    }
    
    public void batchCreate() {
        
        try {
            String[] names = batchName.split(",");
            boolean complete = true;
            for (int i = 0; i < names.length; i++) {
                try {
                    Artist artist = new Artist();
                    artist.setTitle(names[i]);
                    service.persist(artist);
                } catch (Exception ex) {
                    complete = false;
                    LOG.log(Level.WARNING, "[ArtistBean] batchCreate() Error: {0}", ex.toString());
                }
            }
            
            if (complete) {
                MessageUtil.addGlobalInfoMessage("artist-batch-create-artist-sucessfull");
                batchName = null;
            } else {
                MessageUtil.addGlobalWarnMessage("artist-any-artist-has-created-successfully");
            }
            
        } catch (Exception e) {
            MessageUtil.addGlobalErrorMessage("artist-error-when-batch-create-artists", e);
        }
    }

    @Override
    protected Artist initEntity() {
        return new Artist();
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    
}
