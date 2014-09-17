/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb.impl;

import com.nms.mediastore.ejb.ApplicationConfig;
import com.nms.mediastore.ejb.UserFacadeLocalBean;
import com.nms.mediastore.entity.Group;
import com.nms.mediastore.entity.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ApplicationConfigImpl implements ApplicationConfig {

    private static final long serialVersionUID = 3250123085047025097L;

    @EJB
    private UserFacadeLocalBean userService;

    @PostConstruct
    public void init() {
        
    }

}
