package com.yttx.yttxps.web.action;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.yttx.yttxps.comm.Config;
import com.yttx.yttxps.comm.UPFileClient;
import com.yttx.yttxps.model.SessionEntity;

public class BaseController {
	
	protected String input;
	
	protected SessionEntity sessionEntity;

	protected Map<String, Object> modelMap;
	
	protected HttpServletRequest request;
	
	static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	private UPFileClient ftpClient;
	
	private static final String IMAGEPATH = Config.getValue("ImagePath");

	private static final String RESOURCEIP = Config.getValue("ResoureIP");

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	public void setModelMap(Map<String, Object> modelMap) {
		this.modelMap = modelMap;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public SessionEntity getSessionEntity() {
		return sessionEntity;
	}

	public void setSessionEntity(SessionEntity sessionEntity) {
		this.sessionEntity = sessionEntity;
	}
	
	private String getFileName(String type, String extName) {
		StringBuffer path = new StringBuffer(type);
		path.append(String.format("%d", System.currentTimeMillis()));
		path.append(String.format("%03d", (int) (Math.random() * 1000)));
		path.append(".");
		path.append(extName);
		return path.toString();
	}
	
	protected String resourceConvertURL(String path, MultipartFile imgFile) {

		if(StringUtils.isBlank(imgFile.getOriginalFilename()))
			return null;
		
		
		String[] token = imgFile.getOriginalFilename().split("[.]");
		String extname =  token[token.length - 1];
		String fileName = getFileName("img", extname);
		

		StringBuffer localPath = new StringBuffer("");
		localPath.append(IMAGEPATH);
		localPath.append(path);

		File targetFile = new File(localPath.toString(), fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			imgFile.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ftpClient.connect();
			ftpClient.changeDirector(path);
			ftpClient.upFile(targetFile);
			ftpClient.closeConnect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		StringBuffer remotePath = new StringBuffer("");
		remotePath.append("http://");
		remotePath.append(RESOURCEIP);
		remotePath.append(path);
		remotePath.append(fileName);

		logger.debug("目标文件  {} 源文件 {}", targetFile.getAbsolutePath(),
				remotePath.toString());

		return remotePath.toString();
	}
	
	protected boolean deleteResourceByURL(String path) {
		boolean ret = false;
		path = path.replace("http://" + RESOURCEIP, "");
		try {
			ftpClient.connect();
			ret = ftpClient.getClient().deleteFile(path);
			ftpClient.closeConnect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		logger.debug("删除目标文件  {} {} ", path, ret);

		return ret;
	}

}
