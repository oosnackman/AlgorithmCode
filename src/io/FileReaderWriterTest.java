package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年11月6日上午1:50:31
 * @Remarks:
 */

public class FileReaderWriterTest {

	public static void main(String[] args) {

	}

	@Test
	public void testFileReader() throws IOException {

		// 1.指操作的文件
		File file1 = new File("helloword.txt");

		// 2.輸入流
		FileReader fr = new FileReader(file1);

		// 3.讀入一個字符
		while (true) {
			int data = fr.read();
			System.out.print((char) data);
	
			if (data == -1)
				break;
		}

		// 3.讀入一個字符
//		int data =0;
//		while (( data = fr.read())!=-1) {
//			System.out.print((char) data);
//		}
//		
		fr.close();

	}

	@Test
	public void testFileReader2() throws IOException {


		File file = new File("helloword.txt");

		FileReader rf = new FileReader(file);
		
		char[] cbuf = new char[5];
		
		int len=0;
		while ( (len=rf.read(cbuf))!=-1 ) {
		
//			方法1
//			for(int i=0;i<len;i++ ) {
//				System.out.print(cbuf[i]);
//			}
			
			//方法2
			String string=new String(cbuf,0,len);
			System.out.print(string);
		}
		
		rf.close();

	}


	@Test
	public void testFileWriter() throws IOException {
		
		File file=new File("hello1.txt");
		
		
		FileWriter fw=new FileWriter(file);
		
		fw.write("我愛你\n");
		fw.write("我愛你");
		fw.close();
	}

	
	@Test
	public void testFileReaderWriter() throws IOException {
		
		
		File srcFile=new File("helloword.txt");
		File desFile=new File("hello5.txt");
		
		FileReader fr= new FileReader(srcFile);
		FileWriter fw=new FileWriter(desFile);
		
		int len=0; //紀錄每次讀入到cbuf陣列中的字符的個數
		char[] cbuf=new char[256];
		
	    while ( (len=fr.read(cbuf))!=-1  ) {
			
	    	fw.write(cbuf, 0, len);
		} 
		
	    fw.close();
	    fr.close();
	   
	}


	@Test
	public void testFileInputStream() throws IOException {
		
		File srcFile=new File("car.jpg");
		File descFile=new File("car3.jpg");
		
		FileInputStream fi=new FileInputStream(srcFile);
		FileOutputStream fo=new FileOutputStream(descFile);
		
//		byte[] cbuf=new byte[256];
//		int len=0;
//		while ( (len=fi.read(cbuf))!=-1) {
//	
//			fo.write(cbuf, 0, len);
//		}
//		
		this.uploadFile(fi,fo);
		
		fo.close();
		fo.close();
	}
	
	
	
	@Test
	public void BufferedStreamTest() throws IOException {
		
		//1.造文件
		File srcfile=new File("car.jpg");
		File descfile=new File("car10.jpg");
		
		//2.造節點流
		FileInputStream fis=new FileInputStream(srcfile);
		FileOutputStream fos=new FileOutputStream(descfile);
		
		//2.造緩衝流
		
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		
		
		
		while(true) {
			
			byte[] buf=new byte[1024];
			int len=bis.read(buf);
			if(len==-1)
				break;
			bos.write(buf, 0, len);
		}
		
		bos.close();
		bis.close();	
		
//		關外層流的時候 會順便一起關內層流,所寫不寫無所謂	
//		fos.close();
//		fis.close();
		
	}
	
	
	public static void uploadFile(InputStream in, OutputStream out) throws IOException {
		BufferedInputStream bis=new BufferedInputStream(in);
		BufferedOutputStream bos=new BufferedOutputStream(out);
		synchronized (bis) {
			synchronized (bos) {
			
				byte[] buffer = new byte[1024];
				while (true) {
					int len = bis.read(buffer);
					if (len == -1)
						break;
					bos.write(buffer, 0, len);
				}
			}
		}
	}
	
	
	@Test
	public void inputStreamReader() throws IOException {
		
		FileInputStream fis=new FileInputStream("hello1.txt");
		
	    InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
		
	    char[] buf=new char[1024];
	    
	    int len=0;
	    while( (len=isr.read(buf)) !=-1) {
	    	
//	    	for(int i=0;i<len;i++) {
//	    		System.out.print((char)buf[i]);
//	    	}
	    	String str=new String(buf,0,len);
	    	System.out.println(str);
	    }
	    
	    isr.close();
	    
	}
	
	
	
	@Test
	public void inputStreamReader2() throws IOException {
		
	
		FileInputStream fis=new FileInputStream(new File("hello1.txt"));
		FileOutputStream fos=new FileOutputStream(new File("hello5.txt"));
		
		InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
		OutputStreamWriter osw=new OutputStreamWriter(fos,"GBK");
		
		int len=0;
		char[] cbuf=new char[1024];
		
		while( (len=isr.read(cbuf))!=-1) {
			
			osw.write(cbuf, 0, len);
		}
		
		
		osw.close();
		isr.close();
	}
	
	public static void uploadBufFile(InputStream in, OutputStream out) throws IOException {
		synchronized (in) {
			synchronized (out) {
				byte[] buffer = new byte[256];
				while (true) {
					int bytesRead = in.read(buffer);
					if (bytesRead == -1)
						break;
					out.write(buffer, 0, bytesRead);
				}
			}
		}
	}
}
