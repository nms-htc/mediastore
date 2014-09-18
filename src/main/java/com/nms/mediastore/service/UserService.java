package com.nms.mediastore.service;

import com.nms.mediastore.entity.User;
import java.util.List;

public interface UserService extends BaseService<User, Long> {
    
    public User findByUP(String username, String password);
    
    public User findByUsername(String username);
    
    public User updateUserPassword(User user, String oldPassword, String newPassword);
    
    public List<User> findAdministrators();

}
