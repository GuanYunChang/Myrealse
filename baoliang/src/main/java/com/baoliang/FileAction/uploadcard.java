package com.baoliang.FileAction;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class uploadcard extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//�ж��û������������ǲ���multipart/form-data
		request.setCharacterEncoding("UTF-8");
        boolean isMultipart=ServletFileUpload.isMultipartContent(request);
        if(!isMultipart){
            System.out.println("�ϴ��ļ����Ͳ���mutipart����,������ֹ");
        	return;
        }
        
        
        
        //���������ļ�����
        DiskFileItemFactory fileItemFactory =  new DiskFileItemFactory();
      //���������ļ���
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
      fileUpload.setHeaderEncoding("UTF-8");// ��������ļ����ϴ�����.
      
      try {
      List<FileItem> list = fileUpload.parseRequest(request);
      Map<String,String> map = new HashMap<String,String>();
      String fileName = null;
      for (FileItem fileItem : list) {
          if(fileItem.isFormField()){
              //����Ǳ���
              String name = fileItem.getFieldName();
              String string = fileItem.getString("utf-8");
              //����ļ���
              map.put(name, string);
          }else{
              //�ϴ���
              fileName = fileItem.getName();
              InputStream is = fileItem.getInputStream();
              // ����ļ�Ҫ�ϴ���·���������·�������Զ��壩:
              String path = this.getServletContext().getRealPath("/back/imgs");
              OutputStream os = new FileOutputStream(path+"/"+fileName);//cs.jpg
              byte[] byts = new byte[1024];
              int len = 0;
              while ( (len = is.read(byts)) != -1 ) {
                  os.write(byts, 0, len);
                  os.flush();
              }
//            IOUtils.copy(is, os);
              is.close();
              os.close();
          }
      
      }
      
//    BeanUtils.populate();   //��ʵ���Ӧ�����Ը���ʵ�壨�ռ����ݣ�
      if (!fileName.equals(null)&&!fileName.equals("")) {
          //��ͼƬ·������ʵ���ĳ������                    
      }
  //��ʵ�������д�뵽���ݿ�
      }catch(Exception e)
      {
    	  
    	  System.out.println("�ϴ��ļ�ʧ��");
      }
}
/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		doGet(request, response);
		}

	
}
