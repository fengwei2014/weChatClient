package slp.tt.ui.web.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.ibm.db2.jcc.am.bf;



public class IpTest {
	public static void main(String[] args) throws IOException {
//		String path = "http://www.baidu.com";
//		URL pageUrl = new URL(path);
//		InputStream inputStream = pageUrl.openStream();
//		BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream));
//		String lineString;
//		StringBuffer pageBuffer = new StringBuffer();
//		while ((lineString = bfReader.readLine()) != null) {
//			pageBuffer.append(lineString);
//		}
//		System.out.println( pageBuffer.toString());
		
		
		String hostString = "www.zhihu.com";
		String fileString= "index.jsp";
		int port = 80;
		Socket s = new Socket(hostString,port);
		
		OutputStream outputStream = s.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream,false);
		printWriter.print("GET"+fileString);
		printWriter.print("Accept"+fileString);
		printWriter.flush();
		
		InputStream inputStream = s.getInputStream();
		BufferedReader bfBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String lineString;
		while ((lineString = bfBufferedReader.readLine()) != null) {
			System.out.println(lineString);
		}
		};
	}
