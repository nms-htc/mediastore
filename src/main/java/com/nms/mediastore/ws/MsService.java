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

    @WebMethod(operationName = "getHotMusicLink")
    public String[][] getHotMusicLink(@WebParam(name = "count") int count, 
            @WebParam(name = "expireTime") int expireTime, 
            @WebParam(name = "timeUnit") int timeUnit) throws Exception;
    
    @WebMethod(operationName = "getDefaultMusicLink")
    public String[] getDefaultMusicLink();

    @WebMethod(operationName = "getHotVideoLink")
    public String[][] getHotVideoLink(@WebParam(name = "count") int count,
                                      @WebParam(name = "expireTime") int expireTime,
                                      @WebParam(name = "timeUnit") int timeUnit) throws Exception;

    @WebMethod(operationName = "getDefaultVideoLink")
    public String[] getDefaultVideoLink();
    
}
