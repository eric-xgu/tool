package xugu;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;



public class Test {
	public static  void main(String[] args) {
		Connection conn1 = null ; 
		try {
		/*静态sql，statement*/
		Class.forName("com.xugu.cloudjdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:xugu://192.168.2.132:5138/system?time_zone='GMT +07:00'", "SYSDBA", "SYSDBA");
		Statement st=conn.createStatement();
		String sql1="select * from test";
		ResultSet rs=st.executeQuery(sql1);
		while(rs.next()){
			String s1=rs.getObject(1).toString();
			String s2=rs.getObject(2).toString();
			String s3=rs.getObject(3).toString();
			System.out.println(s1+':'+s2+":"+s3);
		};
		String sql2="insert into test values(2,'jack','chaoshan.china')";
		Boolean bool=st.execute(sql2);
		if(bool==false) {
			System.out.println("插入成功");
		}
		String sql3="delete from test where id=2";
		Boolean bool3=st.execute(sql3);
		if(bool==false) {
			System.out.println("删除成功");
		}
		String sql_u="update test set id=5 where id=1";
		st.execute(sql_u);
		String sql_f="select f1(3,7)";
		ResultSet rsf=st.executeQuery(sql_f);
		while(rsf.next()) {
			System.out.println(rsf.getInt(1));
		}
		
		
		//prepare执行预编译sql
		String sql_pu="update test set id=1 where id=?";
		PreparedStatement pstu=conn.prepareStatement(sql_pu);
		pstu.setInt(1, 5);
		pstu.execute();
		
		String sql5="select * from test where id=?";
		PreparedStatement pst=conn.prepareStatement(sql5);
		pst.setInt(1, 1);
		ResultSet rs1=pst.executeQuery();
		while(rs1.next()){
			String s1=rs1.getObject(1).toString();
			String s2=rs1.getObject(2).toString();
			String s3=rs1.getObject(3).toString();
			System.out.println(s1+':'+s2+":"+s3);
		};
		
		String sql6="insert into test values(?,?,?)";
		PreparedStatement pst6=conn.prepareStatement(sql6);
		pst6.setInt(1,3);
		pst6.setString(2, "lucy");
		pst6.setString(3, "jinan.china");
		Boolean bool6=pst6.execute();
		
		String sql7="delete from test where id=?";
		PreparedStatement pst7=conn.prepareStatement(sql7);
		pst7.setInt(1, 3);
		pst7.execute();
		
		/* 存储过程
		 * create procedure  add2(a  in integer,b in integer,c out integer) 
			as
			begin
			c:=a+b;
			end
		 */
		String sql="call add2(?,?,?)";
		CallableStatement cs=conn.prepareCall(sql);
		cs.setInt(1, 2);
		cs.setInt(2, 3);
		cs.registerOutParameter(3,java.sql.Types.INTEGER);
		cs.execute();
		int a=cs.getInt(3);
		System.out.println(a);
		
		/*
		String sql8="insert into test values(?,?,?)";
		PreparedStatement pst8= conn.prepareStatement(sql8);
		for(int i=0;i<10;i++) {
		if(i==5) {
			pst8.setString(1,"asdfasfd");
			pst8.setString(2,"cindy");
			pst8.setString(3,"shanghai.china");
			pst8.addBatch();
		}
		pst8.setInt(1,17);
		pst8.setString(2,"cindy");
		pst8.setString(3,"shanghai.china");
		pst8.addBatch();
		}
		int[] count=pst8.executeBatch();
		System.out.println(Arrays.toString(count));
		recv_mode 0 1 2 游标接收，全接收，网络接收2
		*/
		conn1=DriverManager.getConnection("jdbc:xugu://192.168.2.132:5138/system?auto_commit='false'", "SYSDBA", "SYSDBA");
		String sql10="update  test set id=19 where id=18";
		Statement st10=conn1.createStatement();
		st10.execute(sql10);
		String sql9="insert into test values('18','David','hubei.china')";
		//conn1.setAutoCommit(false);
		PreparedStatement pst9=conn1.prepareStatement(sql9);
		pst9.execute();
		conn1.commit();
		/*
		String sql4="create index test_id_idx on test(id)";
		st.execute(sql4);
		
		xuguouguan
		String sql="insert into uq values(?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1,3);
		pst.setString(2,"c");
		pst.setString(3,null);
		pst.addBatch();
		pst.executeBatch();   
		//conn.commit();
		 		 */
		}catch (Exception e) {
			try {
			conn1.rollback();
			}catch (Exception a) {
				a.printStackTrace();
			}
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
