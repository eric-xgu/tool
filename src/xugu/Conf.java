package xugu;

import java.io.FileInputStream;
import java.util.Properties;

public class Conf {
    Properties properties = new Properties();
	public void load_properties(){
		try {
			properties.load(new FileInputStream("conf/xugu.properties"));
		}catch (Exception e) {
			e.printStackTrace();
		}
}	
}
