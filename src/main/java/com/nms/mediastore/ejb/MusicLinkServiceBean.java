/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.MusicLink;
import com.nms.mediastore.service.MusicLinkService;
import com.nms.mediastore.service.MusicService;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class MusicLinkServiceBean extends AbstractService<MusicLink> implements MusicLinkService {

    private static final long serialVersionUID = -788220173117031208L;
    private static final Logger LOGGER = Logger.getLogger(MusicLinkService.class.getName());

    @EJB
    private MusicService musicService;

    public MusicLinkServiceBean() {
        super(MusicLink.class);
    }

    @Override
    public List<String> getHotMusicLink() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MusicLink findByUUID(String uuid) {
        TypedQuery<MusicLink> q = em.createNamedQuery("MusicLink.findByUUID", MusicLink.class);
        q.setParameter("uuid", uuid);
        return q.getSingleResult();
    }
}
