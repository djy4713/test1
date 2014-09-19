package nlp.yuqing.dz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * 文件读写工具类
 */
public class FileUtil {
	
	public static String readFileToString(String filePath){
	     File myFile=new File(filePath);
	     StringBuffer buffer = new StringBuffer();
	     try {
	         FileInputStream fis = new FileInputStream(myFile);
	         InputStreamReader isr = new InputStreamReader(fis, "utf-8");
	         Reader in = new BufferedReader(isr);
	         int i;
	         while ((i = in.read()) > -1) {
	        	 buffer.append((char) i);
	         }
	         in.close();
	         return buffer.toString();
	     } catch (IOException e) {
	    	 e.printStackTrace();
	     }
	     return null;
	}
	
	public static void writeStringToFile(String filePath,String info){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			OutputStreamWriter osr = new OutputStreamWriter(fos, "utf-8");
			osr.write(info);
			osr.flush();
			osr.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
