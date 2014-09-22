package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.Group;
import com.nms.mediastore.entity.User;
import com.nms.mediastore.service.ApplicationConfig;
import com.nms.mediastore.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ApplicationConfigBean implements ApplicationConfig {

    private static final long serialVersionUID = 3250123085047025097L;
    private static final Logger LOGGER = Logger.getLogger(ApplicationConfigBean.class.getName());
    
    @EJB
    private UserService userService;
    
    @PostConstruct
    public void createDefaultUser() {
        List<User> admins = userService.findAdministrators();
        if (admins == null || admins.isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setFullname("Administrator");
            admin.setPassword("admin");
            List<Group> groups = new ArrayList<>();
            groups.add(Group.Administrator);
            admin.setGroups(groups);
            admin.setEmail("duymb@nms.com.vn");
            admin.setDescription("Default Administrator User");
            userService.persist(admin);
        }
    }

}
