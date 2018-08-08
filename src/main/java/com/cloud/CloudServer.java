package com.cloud;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;





import com.mathworks.toolbox.javabuilder.*;
import NBC.Class1;




public class CloudServer extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "~/IncomingFiles";
    private final String REGISTRY_DIRECTORY = "~/RegisteredUsers";
    private int check_value = 0;
    private String filepath;
    private String name = null;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
		
		
		
		
		
		
		
		
		  if(ServletFileUpload.isMultipartContent(request)){
	            try {
	                List<org.apache.commons.fileupload.FileItem> multiparts = new ServletFileUpload(
	                                         new DiskFileItemFactory()).parseRequest(request);
	              
	                for(org.apache.commons.fileupload.FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        name = new File(item.getName()).getName();
	                        filepath = UPLOAD_DIRECTORY + File.separator + name;
	                        item.write( new File(filepath));
	                    }
	                }
//	                String check_filePath = REGISTRY_DIRECTORY + File.separator + name;
//
//	               //File uploaded successfully
//	                
//	                
//	                
//	        		Object[] resltsum = null;
//	        		
//	        		
//	        		Class1 m = new Class1();
//	        		
//	        		
//	        		resltsum = m.NBC(1, filepath,check_filePath);
//	        		
//	        		MWArray javaSum = (MWNumericArray)resltsum[0];
//	        		
//	        		double[][] total = (double[][])javaSum.toArray();
//	        		
//	        		int finalResult = (int) total[0][0];
	             check_value = authenticate(filepath,name);
	             if(check_value == 1)
	            	 response.setStatus(200);
	                
	             else
	            	 response.setStatus(404);
	                
	                
	               //request.setAttribute("message", "File Uploaded Successfully");
	               
	            } catch (Exception ex) {
	            	response.setStatus(404);
	            }          
	         
	        }else{
	        	response.setStatus(404);
	            
	        }

		
	}


	private int authenticate(String pathToFile,String file_name) throws FileNotFoundException, RemoteException, MWException {
		// TODO Auto-generated method stub
		String pathToEdfFile = pathToFile;
		String check_filePath = REGISTRY_DIRECTORY + File.separator + file_name;

		//MWCharArray check_signal = new MWCharArray(pathToEdfFile);
//		File incoming_signal = new File(pathToEdfFile);
//		File check_signal = new File(check_filePath);
//		
//		MWCtfFileSource input = new MWCtfFileSource(incoming_signal); 
//		MWCtfFileSource check = new MWCtfFileSource(check_signal); 
		
		

//		InputStream is = new BufferedInputStream(new FileInputStream(new File(pathToEdfFile)));
//
//		EDFParserResult result = EDFParser.parseEDF(is);

//		Class1Remote nbc = null;
//		@SuppressWarnings("null")
//		Object[] result = nbc.NBC(1,pathToEdfFile,check_filePath);
		
		Object[] resltsum = null;
		
		
		Class1 m = new Class1();
		
		
		resltsum = m.NBC(1, pathToEdfFile,check_filePath);
		
		MWArray javaSum = (MWNumericArray)resltsum[0];
		
		double[][] total = (double[][])javaSum.toArray();
		
		int finalResult = (int) total[0][0];
		
		if(finalResult == 1)
			return 1;
		else
			return 0;
	}



	

}
