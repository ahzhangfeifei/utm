package com.utm.basic.tool;

import java.io.File;
import java.io.IOException;


public class PathUtil {
    private static String webroot = null;
    static {
        webroot = getWebrootPath();
    }

    private final static String getWebrootPath() {
        String root = PathUtil.class.getResource("/").getFile();
        try {
            root = new File(root).getParentFile().getParentFile()
                    .getCanonicalPath();
            root += File.separator;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }

    /**
     * 返回Web应用的路径
     *
     * @return
     */
    public static String root() {
        return webroot;
    }


}
