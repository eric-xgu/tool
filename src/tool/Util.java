package tool;

public class Util {
	//获取字符串unicode编码
	public static StringBuffer buffer = new StringBuffer();
	public static String strTo16(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);		//获取的为unicode编码
	        String s4 = Integer.toHexString(ch);
	        str = str + s4;
	    }
	    return str;
	}
	
	//int转byte[]
	public static byte[] intToByteArray(int i) {  
	    byte[] result = new byte[4];  
	    // 由高位到低位  
	    result[0] = (byte) ((i >> 24) & 0xFF);  
	    result[1] = (byte) ((i >> 16) & 0xFF);  
	    result[2] = (byte) ((i >> 8) & 0xFF);  
	    result[3] = (byte) (i & 0xFF);  
	    return result;  
	}
	
	public static byte[] intToByteArray3(int i) {  
	    byte[] result = new byte[3];  
	    // 由高位到低位    
	    result[0] = (byte) ((i >> 16) & 0xFF);  
	    result[1] = (byte) ((i >> 8) & 0xFF);  
	    result[2] = (byte) (i & 0xFF);  
	    return result;  
	}
	public static byte[] intToByteArray2(int i) {  
	    byte[] result = new byte[2];  
	    // 由高位到低位  
	    result[0] = (byte) ((i >> 24) & 0xFF);  
	    result[1] = (byte) ((i >> 16) & 0xFF);  
	    result[2] = (byte) ((i >> 8) & 0xFF);  
	    result[3] = (byte) (i & 0xFF);  
	    return result;  
	}
	//byte数组转int
	public static int byteArrayToInt(byte[] bytes) {  
	    int value = 0;  
	    // 由高位到低位  
	    for (int i = 0; i < 4; i++) {  
	        int shift = (4 - 1 - i) * 8;  
	        value += (bytes[i] & 0x000000FF) << shift;// 往高位游  
	    }  
	    return value;  
	}  
	
	//获取hex码
	public static void getHex(byte b[]) {
		//StringBuffer buffer = new StringBuffer();
        String str="";
		for (int j = 0; j < b.length; ++j){
        //buffer.append(toHexString1(b[j]));
        	String s4=toHexString1(b[j]);
        	str+=s4;
        }
        System.out.println(str);
	}
	
	public static String toHexString1(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    
	}
	
}
