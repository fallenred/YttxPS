package com.yttx.yttxps.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yttx.comm.StringUtil;








/**
 * 
 * FTP 客户端封装类
 * 
 * @author Administrator
 *
 */
public class UPFileClient {
	
	private static Logger logger = LoggerFactory.getLogger(UPFileClient.class);
    
	
	private String server;
	private int port;
	private String remotePath;
	private String username;
	private String password;
	FTPClient client;

	
		/**
	     *  连接FTP服务器并改变选程FTP服务器路径
		 * @author kereny
		 * @date 2015-6-9 下午5:01:09
		 * @throws Exception
		 * void
	     *
		 */
	
	public void connect() throws Exception {
		client = new FTPClient();
		client.connect(server, port);
		client.login(username, password);
		int reply = client.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			client.disconnect();
		}
		
		logger.debug("连接服务器 [{}:{}]成功", server, port);
		client.changeWorkingDirectory(remotePath);
		client.setFileTransferMode(FTP.BINARY_FILE_TYPE);    
		client.setFileType(FTP.BINARY_FILE_TYPE); 
		
		logger.debug("进入远程目录 {}", remotePath);
	}
	
	
	public void changeDirector(String path) throws Exception {
		String pathTree[] = path.split("[/]");
		for(int i = 0; i < pathTree.length; i++)
		{
			if(StringUtil.nullOrBlank(pathTree[i])) continue;
			if(!client.changeWorkingDirectory(pathTree[i]))
			{
				client.makeDirectory(pathTree[i]);
				client.changeWorkingDirectory(pathTree[i]);
			}	
		}    
	}

		/**
	     *  上传指定文件或目录到服务器
		 * @author kereny
		 * @date 2015-6-9 下午5:01:38
		 * @param file
		 * @throws Exception
		 * void
	     *
		 */
	public void upFile(File file) throws Exception {
		
		 if(file.isDirectory()){           
			 client.makeDirectory(file.getName());                
			 client.changeWorkingDirectory(file.getName());      
	            String[] files = file.list();             
	            for (int i = 0; i < files.length; i++) {      
	                File file1 = new File(file.getPath()+"\\"+files[i] );      
	                if(file1.isDirectory()){      
	                	upFile(file1);      
	                	client.changeToParentDirectory();      
	                }else{                    
	                    File file2 = new File(file.getPath()+"\\"+files[i]);      
	                    FileInputStream input = new FileInputStream(file2);      
	                    client.storeFile(file2.getName(), input);      
	                    input.close();                            
	                }                 
	            }      
	        }else{      
	            File file2 = new File(file.getPath());      
	            FileInputStream input = new FileInputStream(file2);      
	            client.storeFile(file2.getName(), input);      
	            input.close();        
	            
	            logger.debug("开始传送文件 {}", file2.getName());
	        }      
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FTPClient getClient() {
		return client;
	}

	public void setClient(FTPClient client) {
		this.client = client;
	}
	
	
	public void closeConnect(){
		
		try {
			this.client.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		UPFileClient ftpclient  = new UPFileClient();
		
		ftpclient.setPort(21);
		ftpclient.setServer("192.168.1.61");
		ftpclient.setUsername("qianlong");
		ftpclient.setPassword("qianlong");
		
		ftpclient.setRemotePath("./");
		
		
		File file  = new File("C:/TEMP/03001001");
		
		
		
		try {
			
			ftpclient.connect();
			ftpclient.upFile(file);
			ftpclient.closeConnect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
