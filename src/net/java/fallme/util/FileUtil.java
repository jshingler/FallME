/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.util;

import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    private static final int BUFFER_SIZE = 256;

    public static final String fileAsString(String fileName) {
        byte[] buffer = new byte[BUFFER_SIZE];
        StringBuffer stringBuffer = new StringBuffer(2 * BUFFER_SIZE);

        // get an InputStream to the property file; is
        InputStream is = FileUtil.class.getResourceAsStream(fileName);
        if (is != null) {
        // is is used below
        } else {
            return null;
        }

        // get a string with the contents of the file;
        String outString = null;
        try {
            int readBytes = is.read(buffer);
            while (readBytes > 0) {
                String string = new String(buffer, 0, readBytes, "UTF-8");
                stringBuffer.append(string);
                readBytes = is.read(buffer);
            }

            if (stringBuffer.length() > 0) {
                outString = stringBuffer.toString();
            // configString is used below
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }

        }
        return outString;
    }
}
