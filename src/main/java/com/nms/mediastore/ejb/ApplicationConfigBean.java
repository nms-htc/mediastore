package com.nms.mediastore.ejb;

import com.nms.mediastore.service.ApplicationConfig;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ApplicationConfigBean implements ApplicationConfig {

    private static final long serialVersionUID = 3250123085047025097L;

}
