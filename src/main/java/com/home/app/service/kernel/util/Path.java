package com.home.app.service.kernel.util;

/**
 * Created by thuandh on 29/06/2017.
 */
public interface Path {

    String getUploadVideoPath(long videoId) throws Exception;

    String getVideoPathById(long videoId) throws Exception;

    String getImagePath(long imageId) throws Exception;
}
