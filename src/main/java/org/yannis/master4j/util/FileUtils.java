package org.yannis.master4j.util;

import java.io.*;

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

    public static void newFile(String fileName, String content) {
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(content.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("Generating file "+fileName+" error.");
        } catch (IOException e) {
            System.out.println("Generating file "+fileName+" error.");
        }
    }

    public static String read(String domainPath) {
        try {
            Reader reader = new FileReader(domainPath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder content = new StringBuilder();
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
            reader.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
