/**
 * Copyright © 2015 - 2025 Yannis Zhao (zhaoyjun0222@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yannis.master4j.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;

public final class FileUtils {

    public static boolean exist(String path) {
        return new File(path).exists();
    }

    public static boolean mkdir(String path) {
        File dir = new File(path);
        return (dir.exists() && dir.isDirectory()) ? true : dir.mkdir();
    }

    public static boolean mkdirs(String path) {
        File dir = new File(path);
        return (dir.exists() && dir.isDirectory()) ? true : dir.mkdirs();
    }

    public static boolean copyTo(String src, String dist) throws IOException {
        long bytes = Files.copy(Paths.get(src), new FileOutputStream(dist));
        return bytes > 0 ? true : false;
    }

    public static boolean copyTo(String src, OutputStream dist) throws IOException {
        long bytes = Files.copy(Paths.get(src), dist);
        return bytes > 0 ? true : false;
    }

    public static boolean copyTo(String src, String dist, Map<String, String> data) throws IOException {
        File file = new File(src);
        Long size = file.length();
        byte[] bytes = new byte[size.intValue()];
        try (FileInputStream is = new FileInputStream(src)) {
            is.read(bytes);
        }
        String content = new String(bytes, "UTF-8");

        for (String key : data.keySet()) {
            content = Pattern.compile("\\$\\{" + key + "\\}").matcher(content).replaceAll(data.get(key));
        }

        newFile(dist, content);

        return false;
    }

    public static void newFile(String fileName, String content) {
        FileOutputStream out = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
            }
            out = new FileOutputStream(file);
            out.write(content.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("Generating file " + fileName + " error.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Generating file " + fileName + " error.");
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String read(String domainPath) {
        try {
            Reader reader = new FileReader(domainPath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder content = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
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
