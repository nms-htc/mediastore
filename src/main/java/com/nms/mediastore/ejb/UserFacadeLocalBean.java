package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.User;
import javax.ejb.Local;

@Local
public interface UserFacadeLocalBean extends BaseService<User, Long> {
    
    public User findByUP(String username, String password);
    
    public User findByUsername(String username);
    
    public User updateUserPassword(User user, String oldPassword, String newPassword);

}
