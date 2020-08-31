package org.dxl.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 用于关系流
 *
 * @author Administrator
 */
public class IoCloseUtil {
    public static void closeAll(Closeable... close) {
        if (close == null || close.length == 0) {
            return;
        }
        for (Closeable closeable : close) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
