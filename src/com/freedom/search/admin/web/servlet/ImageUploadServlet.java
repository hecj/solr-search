package com.freedom.search.admin.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.MessageCode;
/**
 * @类功能说明：图片上传
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2015-1-20 下午08:27:31
 * @版本：V1.0
 */
public class ImageUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private String uploadPath = "upload/upload/"; //上传文件的目录  
	private String tempPath = "upload/uploadtmp/"; //临时文件目录  
	private String[] fileType = new String[]{".jpg",".gif",".bmp",".png",".jpeg",".ico"};  

	private String serverPath = null;  
	private int sizeMax = 3; //图片最大上限  
		
	@Override
	public void init() throws ServletException {
		//绝对路径
		serverPath = getServletContext().getRealPath("/").replace("\\", "/");  
		//Servlet初始化时执行,如果上传文件目录不存在则自动创建  
	    if(!new File(serverPath+uploadPath).isDirectory()){  
	    	new File(serverPath+uploadPath).mkdirs();  
	    }  
	    if(!new File(serverPath+tempPath).isDirectory()){  
	        new File(serverPath+tempPath).mkdirs();  
	    }
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("上传图片============");
		PrintWriter out = response.getWriter();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//最大缓存
			factory.setSizeThreshold(5*1024);
			//临时文件目录 
	        factory.setRepository(new File(serverPath+tempPath));
			ServletFileUpload upload = new ServletFileUpload(factory);
			//文件最大上限
			upload.setSizeMax(sizeMax*1024*1024);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem itemFile : items) {
              if(!itemFile.isFormField()){
            	 //获得文件名，这个文件名包括路径
                 String fileName = itemFile.getName().toLowerCase();
                 //文件后缀名
                 String extensionName = fileName.substring(fileName.lastIndexOf("."));
				 //新文件名
                 String newFileName = UUIDUtil.autoUUID();
				 String filePath = uploadPath+newFileName+extensionName;
				 itemFile.write(new File(serverPath+filePath));
				 out.write(JSON.toJSONString(new MessageCode("0", filePath)));
				 //只取第一个图片
				 return;
              }
			}
		} catch (FileUploadException e) {
			out.write(JSON.toJSONString(new MessageCode("1", e.getMessage())));
			e.printStackTrace();
		} catch (Exception e) {
			out.write(JSON.toJSONString(new MessageCode("1", e.getMessage())));
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
	}
}
