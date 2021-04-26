package xugu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Test1 {
	public static  void main(String[] args){
		try {

		File block=new File("D:/block.sql");
		FileReader fr =new FileReader(block);
		/*
		char[] buffer=new char[20];
		fr.read(buffer);
		System.out.println(buffer);
		*/
		int i=fr.read();
		String str="\\u"+Integer.toHexString(i);
		System.out.println(str);
		int i1=fr.read();
		String b="\u4e2d";
		System.out.println(b);
		String dt="20210331000000";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date  date = simpleDateFormat.parse(dt);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String st=sdf.format(date);
		System.out.println(st);
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		//Date date=(Date) formatter.parse(dt);
		System.out.println(date);
		
		}catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
	}
	
	
}
