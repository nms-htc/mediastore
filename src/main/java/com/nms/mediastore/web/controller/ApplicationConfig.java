/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.web.controller;

import com.nms.mediastore.entity.Group;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.service.UserService;
import com.nms.mediastore.util.MessageUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationConfig implements Serializable {

    private static final long serialVersionUID = -8192641713325180834L;
    
    @EJB
    private UserService userService;
    
    @PostConstruct
    public void init() {
        List<User> admins = userService.findAdministrators();
        if (admins == null || admins.isEmpty()) {
            User admin = new User();
            Set<Group> groups = new HashSet<>();
            groups.add(Group.Administrator);
            admin.setFullname("Administrator");
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setEmail("duymb@nms.com.vn");
            try {
                userService.persist(admin);
            } catch (Exception e) {
                MessageUtil.addGlobalErrorMessage("error-when-create-default-user", e);
            }
            
                    
        }
    }

}
