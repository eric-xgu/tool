package xugu;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.zaxxer.hikari.HikariDataSource;

public class HikariUtils {
	private static final Logger logger = Logger.getLogger(HikariUtils.class);//log4j
	//private static final Logger logger1 = LoggerFactory.getLogger(HikariUtils.class);//slf4j就是获取logger对象不同
	
	public static HikariDataSource hkxugudataSource=new HikariDataSource();
	public static HikariDataSource hkmysqldataSource=new HikariDataSource();
	public static HikariDataSource hkoracledataSource=new HikariDataSource();
	public static Connection conn;
	public static void initxuguPool() {
		hkxugudataSource.setDriverClassName("com.xugu.cloudjdbc.Driver");
		hkxugudataSource.setJdbcUrl("jdbc:xugu://192.168.2.132:5138/system?ips=192.168.2.134,192.168.2.133&char_set=utf8");
		hkxugudataSource.setUsername("SYSDBA");
		hkxugudataSource.setPassword("SYSDBA");
		//最小连接数,连接保留数
		hkxugudataSource.setMinimumIdle(2);
		//最大连接数
		hkxugudataSource.setMaximumPoolSize(10);
		// 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
		//客户端等待连接数，缺省:30秒，建议设置比数据库超时时长少30秒，
		//max_idle_time, wait_timeout参数（show variables like '%timeout%';）
		hkxugudataSource.setConnectionTimeout(60000);

		//允许连接在连接池中空闲时间。 此设置仅适用于minimumIdle定义为小于maximumPoolSize。
		//一旦池达到连接，空闲连接将不会退出minimumIdle。
		hkxugudataSource.setIdleTimeout(6000);
		
		//此属性控制池中连接的最大生存期。正在使用的连接永远不会退休，只有在关闭后才会被删除。
		//在逐个连接的基础上，应用较小的负面衰减来避免池中的大量消失。 强烈建议设置此值，并且应该比任何数据库或基础设施规定的连接时间限制短几秒。 值为0表示没有最大寿命（无限寿命），当然是idleTimeout设定的主题。 默认值：1800000（30分钟）
		hkxugudataSource.setMaxLifetime(10000);
		
		//Connection.isValid() 
		hkxugudataSource.setConnectionTestQuery("select 1");
		//metricRegistry,healthCheckRegistry 该属性仅通过编程配置或IoC容器可用
		//此属性控制连接测试活动的最长时间。这个值必须小于connectionTimeout。最低可接受的验证超时时间为250 ms。 默认值：5000
		hkxugudataSource.setValidationTimeout(2000);
		hkxugudataSource.setAutoCommit(true);

		//如果池无法成功初始化连接，则此属性控制池是否将“快速失败”。
		hkxugudataSource.setInitializationFailTimeout(10000);
		//leakDetectionThreshold此属性控制在记录消息之前连接可能离开池的时间量，表明可能存在连接泄漏。值为0意味着泄漏检测被禁用。启用泄漏检测的最低可接受值为2000（2秒）。 默认值：0
		//hkdataSource.setLeakDetectionThreshold(10000);
		
		
		
		//schema该属性设置的默认模式为支持模式的概念数据库
		//hkdataSource.setLoginTimeout(5);
		//isolateInternalQueries 此属性确定HikariCP是否在其自己的事务中隔离内部池查询，例如连接活动测试。
		//allowPoolSuspension 该属性控制池是否可以通过JMX暂停和恢复,当池被暂停时，呼叫 getConnection()将不会超时，并将一直保持到池恢复为止。 默认值：false
		//registerMbeans 该属性控制是否注册JMX管理Bean（“MBeans”）。 默认值：false
		//catalog 该属性设置默认目录为支持目录的概念数据库。如果未指定此属性，则使用由JDBC驱动程序定义的默认目录。
		//connectionInitSql该属性设置默认目录为支持目录的概念数据库。如果未指定此属性，则使用由JDBC驱动程序定义的默认目录。
		//transactionIsolation 此属性控制从池返回的连接的默认事务隔离级别。
		
		
	}
	public static void initmysqlPool() {
		hkmysqldataSource.setDriverClassName("com.mysql.jdbc.Driver");
		hkmysqldataSource.setJdbcUrl("jdbc:mysql://192.168.2.212:3309/?char_set=utf8&useSSL=false");
		hkmysqldataSource.setUsername("root");
		hkmysqldataSource.setPassword("123456");

		hkmysqldataSource.setMinimumIdle(2);

		hkmysqldataSource.setMaximumPoolSize(10);

		//hkmysqldataSource.setConnectionTimeout(60000);


		hkmysqldataSource.setIdleTimeout(6000);
		
		hkmysqldataSource.setMaxLifetime(10000);
		
		//Connection.isValid() 
		hkmysqldataSource.setConnectionTestQuery("select 1");
		hkmysqldataSource.setValidationTimeout(2000);
		hkmysqldataSource.setAutoCommit(true);

		hkmysqldataSource.setInitializationFailTimeout(10000);
	}
	public static void initoraclePool() {
		//logger.debug("正在初始化");
		hkoracledataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		hkoracledataSource.setJdbcUrl("jdbc:oracle:thin:@192.168.2.133:1521:fxdb");
		hkoracledataSource.setUsername("sys as sysdba");
		hkoracledataSource.setPassword("123456");
		
		hkoracledataSource.setMinimumIdle(2);

		hkoracledataSource.setMaximumPoolSize(10);

		hkoracledataSource.setConnectionTimeout(60000);


		hkoracledataSource.setIdleTimeout(6000);
		
		hkoracledataSource.setMaxLifetime(10000);
		
		//Connection.isValid() 
		hkoracledataSource.setConnectionTestQuery("select 1 from dual");
		hkoracledataSource.setValidationTimeout(2000);
		hkoracledataSource.setAutoCommit(true);

		hkoracledataSource.setInitializationFailTimeout(10000);
	}
	
	
	public static Connection getxuguConnection(){
        try {
        	return hkxugudataSource.getConnection();

        } catch (Exception e) {
        //    logger.error("Exception in C3p0Utils!", e);
      //      throw new MyError("数据库连接出错!", e);            
        }
       return null;
    }  
	
	public static Connection getmysqlConnection(){
        try {
        	return hkmysqldataSource.getConnection();

        } catch (Exception e) {
        //    logger.error("Exception in C3p0Utils!", e);
      //      throw new MyError("数据库连接出错!", e);            
        }
       return null;
    }    
	public static Connection getoracleConnection(){
        try {
        	return hkoracledataSource.getConnection();

        } catch (Exception e) {
        //    logger.error("Exception in C3p0Utils!", e);
      //      throw new MyError("数据库连接出错!", e);            
        }
       return null;
    } 
}
