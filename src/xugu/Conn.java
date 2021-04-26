package xugu;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xugu.cloudjdbc.PreparedStatement;
import com.xugu.cloudjdbc.ResultSet;
import com.xugu.cloudjdbc.Statement;
import tool.Util;


public class Conn {
	/**
	 * 字符串转化成为16进制字符串
	 * @param s
	 * @return
	 */
	
		/**
		 * @param args
		 */
		public static  void main(String[] args) {
	        // 声明Connection对象
	        Connection con;
	        PreparedStatement ps = null;
	        // 驱动程序名
	        String driver = "com.xugu.cloudjdbc.Driver";
	        // URL指向要访问的数据库名 test
	        String url = "jdbc:xugu://192.168.2.132:5138/system?char_set=utf8&time_zone='GMT +07:00'";
	        // MySQL配置时的用户名
	        String user = "SYSDBA";
	        // MySQL配置时的密码
	        String password = "SYSDBA";
	        // 遍历查询结果集
	        String col="☺";
	        byte [] colb=col.getBytes();
	        try {
	            // 加载驱动程序
	            Class.forName(driver);
	            // 1.getConnection()方法，连接MySQL数据库！！
	            con = (Connection) DriverManager.getConnection(url, user, password);
	            if (!con.isClosed())
	                System.out.println("\n\t\t成功以 " + user + " 身份连接到数据库！！！");
	 
	            // 2.创建statement类对象，用来执行SQL语句！！
	            Statement statement = (Statement) con.createStatement();
	            
	            // 要执行的SQL语句
	            String sql = "select * from all_type_tab";
	            // 3.ResultSet类，用来存放获取的结果集！！
	            ResultSet rs = (ResultSet) statement.executeQuery(sql);

	            InputStream ID ;
	            String ID1 = "";
	            byte [] b;
	            byte [] lenb;
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            int readSize = 0;//每次读取的字节长度
	            int totalSize = 0;//总字节长度
	            OutputStream os = System.out;
	            OutputStream out = new FileOutputStream("d:/a",true);
	            byte[] buff = new byte[64*1024];
	            while (rs.next()) {
	            for(int i=1;i<=10;i++) {
	            		b=rs.getBytes(i);
	            		InputStream is = new ByteArrayInputStream(b);
		                int len = 0;
		                while((len=is.read(buff))!=-1){
		                	lenb=Util.intToByteArray(len);
		                	out.write(lenb);
		                    out.write(buff, 0, len);
		                }
		                System.out.println(b.length);
		           	    Util.getHex(b);     
		                //Util.buffer.delete(0,Util.buffer.length());
	            		}
	            }
	        }catch (Exception e) {
				e.printStackTrace();
			}
		
	}
}

