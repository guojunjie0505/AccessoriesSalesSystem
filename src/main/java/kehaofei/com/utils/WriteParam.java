package kehaofei.com.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class WriteParam {
	

    
	
	static String s = System.getProperty("user.dir") + "\\message.properties";
	
	public void setParam(String key , String value){
		
		try {
			Properties prop = new Properties();// 属性集合对象
//			URL url = WriteParam.class.getResource("seqkey.properties");
			
			File file = new File(s);

			InputStream fis = new FileInputStream(file);

			prop.load(fis);// 将属性文件流装载到Properties对象中

			fis.close();// 关闭流
			
			prop.setProperty(key, value);// 添加数据
			
			// 文件输出流
			FileOutputStream fos = new FileOutputStream(file);
			// 将Properties集合保存到流中
			prop.store(fos, null);
			fos.flush();
			fos.close();// 关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * 获取参数值
	 * @author XCCD
	 * @param key
	 * @return
	 */
	public String getParam(String key){
		ResourceBundle mySeqkey = null;  
		
		BufferedInputStream inputStream = null;  
	     
	    String proFilePath = System.getProperty("user.dir") + "\\message.properties";  
        try {  
            inputStream = new BufferedInputStream(new FileInputStream(proFilePath));  
            mySeqkey = new PropertyResourceBundle(inputStream);  
             
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{
        	try {
        		if(inputStream != null){
        			inputStream.close();
        		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
		return mySeqkey.getString(key);
		
	}
}

