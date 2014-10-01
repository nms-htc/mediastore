/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author CuongNT
 */
@WebService(name = "MediaService")
public interface MsService {

    @WebMethod
    public String[] getHotMusicLink(@WebParam(name = "count") int count, 
            @WebParam(name = "expireTime") int expireTime, 
            @WebParam(name = "timeUnit") int timeUnit) throws Exception;
    
    public String getDefaultMusicLink();
    
}
