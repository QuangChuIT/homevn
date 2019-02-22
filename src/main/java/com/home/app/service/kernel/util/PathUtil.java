package com.home.app.service.kernel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by thuandh on 29/06/2017.
 */
public class PathUtil {

    public static String getUploadVideoPath(long videoId) throws Exception{
        return getService().getUploadVideoPath(videoId);
    }

    public static String getVideoPathById(long videoId) throws Exception{
        return getService().getVideoPathById(videoId);
    }

    public static String getImagePath(long imageId) throws Exception{
        return getService().getImagePath(imageId);
    }

    public static Path getService(){
        return _service;
    }

    public void setPath(Path path){
        _service = path;
    }

    private static Path _service;
}
