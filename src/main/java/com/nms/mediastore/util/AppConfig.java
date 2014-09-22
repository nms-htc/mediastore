/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConfig implements Serializable {

    private static final long serialVersionUID = 6615311407288469977L;
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());
    public static final String FILE_PATH = "config.properties";
    public static final String FILE_STORE_PATH_PROPERTY = "filestore.path";

    public static Properties props = null;

    static {
        try {
            loadProperties();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "[AppConfig] can not load 'config.properties'.", e);
        }
    }

    private static void loadProperties() throws IOException {
        InputStream is = AppConfig.class.getClassLoader().getResourceAsStream(FILE_PATH);
        props = new Properties();
        props.load(is);
    }

    public String getFileStorePath() {
        String path = null;

        if (props != null) {
            path = props.getProperty(FILE_STORE_PATH_PROPERTY);
        }
        
        if (path == null) {
            path = getDefaultPath();
        }

        return path;
    }

    private String getDefaultPath() {
        StringBuilder sb = new StringBuilder(System.getProperty("user.home"));
        sb.append(File.separator);
        sb.append(".mediastorefile").append(File.separator);
        return sb.toString();
    }
}
