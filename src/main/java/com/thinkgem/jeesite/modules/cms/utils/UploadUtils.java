package com.thinkgem.jeesite.modules.cms.utils;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import com.thinkgem.jeesite.common.config.Global;


public class UploadUtils {

	  public static String upload(InputStream is, String savePath) throws IOException{
	        String htmlName = new Date().getTime() + ".html";
	        String dbPath = savePath + htmlName;
	        String fullPath = Global.getConfig("basePath") + savePath + htmlName;
	        OutputStream os = new FileOutputStream(fullPath);
	        int bytesRead = 0;
	        byte[] buffer = new byte[1024*5];
	        while((bytesRead = is.read(buffer)) != -1){
	            os.write(buffer, 0, bytesRead);
	        }
	        is.close();
	        os.close();
	        return dbPath;
	    }

	    public static Integer getFileByte(InputStream is) {
	        Integer size = null;
	        try {
	             size = is.available();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        return size;
	    }
	    
	    public static InputStream StringTOInputStream(String in,String encoding) throws Exception{  
	          
	        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes(encoding));  
	        return is;  
	    } 
}
