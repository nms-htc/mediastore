/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.MusicLink;
import com.nms.mediastore.service.MusicLinkService;
import java.util.logging.Logger;

public class MusicLinkServiceBean extends AbstractService<MusicLink> implements MusicLinkService {

    private static final long serialVersionUID = -788220173117031208L;
    private static final Logger LOGGER = Logger.getLogger(MusicLinkService.class.getName());

    public MusicLinkServiceBean() {
        super(MusicLink.class);
    }

    @Override
    public String generateMusicLink(Long musicId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
