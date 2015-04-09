/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.VideoLink;
import com.nms.mediastore.service.VideoLinkService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author richard
 */
@Stateless
public class VideoLinkServiceBean extends AbstractService<VideoLink> implements VideoLinkService {

    private static final long serialVersionUID = 239193525433793139L;

    public VideoLinkServiceBean() {
        super(VideoLink.class);
    }

    @Override
    public List<String> getHotVideoLink() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VideoLink findByUUID(String uuid) {
        TypedQuery<VideoLink> q = em.createNamedQuery("VideoLink.findByUUID", VideoLink.class);
        q.setParameter("uuid", uuid);
        return q.getSingleResult();
    }

}
