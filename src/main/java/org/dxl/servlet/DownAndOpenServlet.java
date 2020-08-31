package org.dxl.servlet;

import org.dxl.server.Request;
import org.dxl.server.Response;
import org.dxl.util.IoCloseUtil;
import org.dxl.util.PropUtils;

import javax.print.DocFlavor;
import java.io.*;
import java.net.URL;

/**
 * * @projectName launcher2
 * * @title DownAndOpenServlet
 * * @package org.dxl.servlet
 * * @description  下载文件打开文件服务
 * * @author IT_CREAT     
 * * @date  2020 2020/8/31/031 20:46  
 * * @version 1.0
 */
public class DownAndOpenServlet extends BaseServlet {

    @Override
    public void doGet(Request req, Response rep) throws Exception {
        String fileDownLoadPath = PropUtils.getValue("fileDownLoadPath");
        String downLoadPath = req.getParameter("downLoadPath");
        URL url = new URL(fileDownLoadPath + "?pathName=" + downLoadPath);
        InputStream is = url.openStream();
        byte[] buffer = new byte[1024];
        FileOutputStream tempOutPut = new FileOutputStream(PropUtils.getValue("tempDir") + "/" + new File(downLoadPath).getName());
        int length = 0;
        while ((length = is.read(buffer)) != -1) {
            tempOutPut.write(buffer, 0, length);
        }
        IoCloseUtil.closeAll(is, tempOutPut);
    }

    @Override
    public void doPost(Request req, Response rep) throws Exception {

    }
}
