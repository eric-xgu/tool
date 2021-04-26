package xugu;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;

public class DruidUtils {
	private static final Logger logger = Logger.getLogger(DruidUtils.class);//log4j
	//private static final Logger logger1 = LoggerFactory.getLogger(HikariUtils.class);//slf4j就是获取logger对象不同
	
	public static DruidDataSource drxugudataSource=new DruidDataSource();
	public static DruidDataSource hkmysqldataSource=new DruidDataSource();
	public static DruidDataSource hkoracledataSource=new DruidDataSource();
	public static Connection conn;
	public static void initxuguPool() {
		drxugudataSource.setDriverClassName("com.xugu.cloudjdbc.Driver");
		drxugudataSource.setUrl("jdbc:xugu://192.168.2.132:5138/system?ips=192.168.2.134,192.168.2.133&char_set=utf8");
		drxugudataSource.setUsername("SYSDBA");
		drxugudataSource.setPassword("SYSDBA");
        //下面都是可选的配置
		drxugudataSource.setInitialSize(10);  //初始连接数，默认0
		drxugudataSource.setMaxActive(30);  //最大连接数，默认8
		drxugudataSource.setMinIdle(10);  //最小闲置数
		drxugudataSource.setMaxWait(2000);  //获取连接的最大等待时间，单位毫秒
		drxugudataSource.setPoolPreparedStatements(false); //缓存PreparedStatement，默认false
		drxugudataSource.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement
	}
	public static Connection getxuguConnection(){
        try {
        	return drxugudataSource.getConnection();

        } catch (Exception e) {
        //    logger.error("Exception in C3p0Utils!", e);
      //      throw new MyError("数据库连接出错!", e);            
        }
       return null;
    }  
}
