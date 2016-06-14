package org.yannis.master4j.util;

import java.io.File;

/**
 * Created by yannis on 6/14/16.
 */
public final class FileUtils {

    public static boolean mkdir(String path){
        File dir = new File(path);
        return (dir.exists() && dir.isDirectory())?true:dir.mkdir();
    }

    public static boolean mkdirs(String path){
        File dir = new File(path);
        return (dir.exists() && dir.isDirectory())?true:dir.mkdirs();
    }
}
