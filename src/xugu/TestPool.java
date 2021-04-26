package xugu;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.imageio.plugins.common.InputStreamAdapter;

public class TestPool {
	static Connection conn;
	public static  void main(String[] args) throws InterruptedException {
		
		DruidUtils.initxuguPool();
		/*for(int i=0;i<=15;i++) {
			Thread th=new Thread(new Runnable() {
				@Override
				public void run() {
					conn =HikariUtils.getConnection();
					System.out.println("线程"+Thread.currentThread().getName());
					try {
					Statement st = conn.createStatement();
					//连接池的连接被数据库清理，并未将连接重新放回连接池
					//for(;;) {
					String sql1="select * from test";
					ResultSet rs=st.executeQuery(sql1);
					//while(rs.next()){
					rs.next();
					String s1=rs.getObject(1).toString();
					String s2=rs.getObject(2).toString();
					String s3=rs.getObject(3).toString();
					System.out.println(s1+':'+s2+":"+s3+Thread.currentThread().getName());
					//}
					conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}) ;
			th.start();
		}
		*/
		try {
			conn =DruidUtils.getxuguConnection();
			Statement st=conn.createStatement();
			String sql1="select * from test";
			ResultSet rs=st.executeQuery(sql1);
			while(rs.next()){
				String s1=rs.getObject(1).toString();
				String s2=rs.getObject(2).toString();
				String s3=rs.getObject(3).toString();
				System.out.println(s1+':'+s2+":"+s3);
			};
			String str="declare\r\n" + 
					"cons_t varchar;\r\n" + 
					"stat varchar;\r\n" + 
					"begin\r\n" + 
					"for i in (select \r\n" + 
					"      col_name, comments, type_name, scale, not_null, cons_type,define\r\n" + 
					"      from dba_columns c\r\n" + 
					"      INNER JOIN dba_tables a ON a.table_id = c.table_id\r\n" + 
					"      INNER JOIN dba_schemas b ON a.schema_id = b.schema_id\r\n" + 
					"      INNER JOIN dba_constraints d ON d.table_id = a.table_id \r\n" + 
					"where \r\n" + 
					"      a.table_name = 'TQ' and b.schema_name = 'SYSDBA'  and d.cons_type='P')loop\r\n" + 
					"select instr(i.define,i.col_name) into stat;\r\n" + 
					"if(stat>0) then\r\n" + 
					"cons_t:='T';\r\n" + 
					"dbms_output.put_line(i.col_name||','||i.comments||','||i.type_name||','||i.scale||','||to_char(i.not_null)||','||cons_t);\r\n" + 
					"else\r\n" + 
					"cons_t:='F';\r\n" + 
					"dbms_output.put_line(i.col_name||','||i.comments||','||i.type_name||','||i.scale||','||to_char(i.not_null)||','||cons_t);\r\n" + 
					"end if;\r\n" + 
					"end loop;\r\n" + 
					"end;";
			//ResultSet ts1=st.executeQuery(str);
			//System.out.println(ts1);
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
			
			
			
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
		conn =HikariUtils.getxuguConnection();
		System.out.println(conn);
		Statement st=conn.createStatement();
		String sql1="select hex('中国') from dual";
		ResultSet rs=st.executeQuery(sql1);
		while(rs.next()){
			String s1=rs.getObject(1).toString();
			System.out.println(s1);
		};
		HikariUtils.initmysqlPool();
		conn =HikariUtils.getmysqlConnection();
		System.out.println(conn);
		Statement st1=conn.createStatement();
		String sql="select hex('中国')";
		ResultSet rs1=st1.executeQuery(sql1);
		while(rs1.next()){
			String s1=rs1.getObject(1).toString();
			System.out.println(s1);
			//byte b[]=s1.getBytes();
			//conn.prepareStatement("insert into ");
		};
		HikariUtils.initoraclePool();
		
		conn =HikariUtils.getoracleConnection();
		System.out.println(conn);
		Statement sto=conn.createStatement();
		String sqlo="select dump('中国',1016) from dual";
		ResultSet rso=sto.executeQuery(sqlo);
		while(rso.next()){
			String s1=rso.getObject(1).toString();
			System.out.println(s1);
		};
		String sqlt="select * from t2";
		ResultSet rst=sto.executeQuery(sqlt);
		while(rst.next()){
			InputStream is=rst.getBinaryStream(2);
			byte b[] =new byte[50];
			while(rst.read(b)) {
				
			}
			
		};
		
	
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//Thread.sleep(5000000);
	
	}
}
