package xugu;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.log4j.Logger;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;



public class Run {
	private static final Logger logger = Logger.getLogger(Run.class);
	public static  void main(String[] args) {
		Connection conn=null;
		HikariUtils.initxuguPool();
		try {
		conn =HikariUtils.getxuguConnection();
		DatabaseMetaData mt= conn.getMetaData();
		ResultSet rs=mt.getColumns(null,"%","t1","%");
		System.out.println("start");

		while(rs.next()) {
            // 字段名称
            String columnName = rs.getString("COLUMN_NAME");
            // 数据类型
            String columnType = rs.getString("TYPE_NAME");
            // 字段长度
            int datasize = rs.getInt("COLUMN_SIZE");
            // 小数部分位数
            int digits = rs.getInt("DECIMAL_DIGITS");
            // 是否可为空 1代表可空 0代表不可为空
            int nullable = rs.getInt("NULLABLE");
            // 描述
            String remarks = rs.getString("REMARKS");
            System.out.println(columnName + " " + columnType + " " + datasize + " " + digits + " " + nullable + " " + remarks);
        }
        System.out.println("=================================");
		
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt=null;
		Connection conn=null;
		HikariUtils.initxuguPool();
		try {
			conn =HikariUtils.getxuguConnection();
			Statement st=conn.createStatement();
			String sql1="truncate table t2";
			boolean bool=st.execute(sql1);
			System.out.println(bool);
			/*
			ResultSet rs= st.executeQuery(sql1);
			while(rs.next()){
				int s1=rs.getInt(1);
				String s2=rs.getString(2);
				Timestamp s3=rs.getTimestamp(3);
				System.out.println(s1+':'+s2+":"+sdf.format(s3));
			};
			*/
		/*
		try {
			/*FileWriter fs=new FileWriter("D:/fs");
			for (int i = 49; i < 97; i++) {
				fs.write(i);
				fs.write(' ');
			}
			fs.write("i love you,你呢?");
			fs.close();
		
		
			FileOutputStream fs=new FileOutputStream("D:/fs");
			for (int i = 49; i < 97; i++) {
				fs.write(i);
				fs.write(' ');
			}
			byte[] buffer = "i love you,你呢?".getBytes();
			System.out.println(buffer.length);
			fs.write(buffer);
			fs.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		*/
	}
	
}
