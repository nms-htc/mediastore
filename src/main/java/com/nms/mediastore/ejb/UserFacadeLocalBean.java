/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ejb;

import com.nms.mediastore.entity.User;
import javax.ejb.Local;

/**
 *
 * @author CuongNT
 */
@Local
public interface UserFacadeLocalBean extends BaseService<User, Long> {

}
