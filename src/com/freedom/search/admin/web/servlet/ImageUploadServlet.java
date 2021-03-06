package com.freedom.search.admin.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.alibaba.fastjson.JSON;
import com.freedom.search.admin.vo.AppContext;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.Log4jUtil;
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
	
	private String imageDir = ""; //上传文件的目录  
	private String imageDirTmp = ""; //临时文件目录  
	private String[] fileType = new String[]{".jpg",".gif",".bmp",".png",".jpeg",".ico"};  

	private String serverPath = ""; 
	private int sizeMax = 3; //图片最大上限  
		
	@Override
	public void init() throws ServletException {

		serverPath = AppContext.getParentDir();
	    imageDir = AppContext.getImageDir();
	    imageDirTmp = AppContext.getImageDirTmp(); 
	    
	    if(!new File(serverPath+imageDir).isDirectory()){  
	    	new File(serverPath+imageDir).mkdirs();  
	    }  
	    if(!new File(serverPath+imageDirTmp).isDirectory()){  
	        new File(serverPath+imageDirTmp).mkdirs();  
	    }
	    Log4jUtil.info("初始化上传路径："+serverPath+imageDir);
	    Log4jUtil.info("初始化上传临时路径："+serverPath+imageDirTmp);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Log4jUtil.info("上传图片开始");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//最大缓存
			factory.setSizeThreshold(5*1024);
			//临时文件目录 
	        factory.setRepository(new File(serverPath+imageDirTmp));
			ServletFileUpload upload = new ServletFileUpload(factory);
			//文件最大上限3M
			upload.setSizeMax(sizeMax*1024*1024);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem itemFile : items) {
              if(!itemFile.isFormField()){
            	  
            	 //获得文件名，这个文件名包括路径
                 String fileName = itemFile.getName().toLowerCase();
                 //文件后缀名
                 String extensionName = fileName.substring(fileName.lastIndexOf("."));
                 boolean b = false;
	           	 for(String type : fileType){
	           		 if(extensionName.endsWith(type)){
	           			 b = true;
	           		 } 
	           	 }
	           	 if(!b){
	           		 out.write(JSON.toJSONString(new MessageCode("1", "上传文件类型错误："+itemFile.getName())));
	           		 break;
	           	 }
                 //新文件名
                 String newFileName = UUIDUtil.autoUUID();
				 String filePath = imageDirTmp+newFileName+extensionName;
				 itemFile.write(new File(serverPath+filePath));
				 out.write(JSON.toJSONString(new MessageCode("0", filePath)));
				 Log4jUtil.log("上传图片成功:"+filePath);
				 //只取第一个图片
				 return;
              }
			}
		} catch (SizeLimitExceededException e) {
			out.write(JSON.toJSONString(new MessageCode("1", e.getMessage())));
			e.printStackTrace();
		} catch (FileUploadException e) {
			out.write(JSON.toJSONString(new MessageCode("1", e.getMessage())));
			e.printStackTrace();
		} catch (Exception e) {
			out.write(JSON.toJSONString(new MessageCode("1", e.getMessage())));
			e.printStackTrace();
		} finally{
			Log4jUtil.info("上传图片结束！");
			out.flush();
			out.close();
		}
	}
}
