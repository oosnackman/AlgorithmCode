package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * Description:
 * @author MarkLin
 * @Date:2019年11月6日下午9:13:32
 * @Remarks:
 */

public class Nio {

	public static void main(String[] args) throws IOException  {
	
		
		FileUtils.copyFile(new File("car.jpg"), new File("car100.jpg"));
	}
	
	@Test
	public void nio() throws IOException {
	Path src=Paths.get("car.jpg");
		
		System.out.println(src.getFileName());
		
		FileInputStream fis=new FileInputStream(src.toFile());
		FileOutputStream fos=new FileOutputStream("car10.jpg");
		
		
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		
		
		byte[] cbuf=new byte[1024];
		int len=0;
		while( (len=fis.read(cbuf))!=-1) {
			
			bos.write(cbuf, 0, len);
		}
		
		bos.close();
		bis.close();
		
	}



}
