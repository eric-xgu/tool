package xugu;

public class ToolUtils {
	public static String printHexString(byte b){
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex.toUpperCase();
   }


	public static String printHexString( byte[] b) {
       String strHex = "";
       for (int i = 0; i < b.length; i++) {
           String hex = Integer.toHexString(b[i] & 0xFF);
           if (hex.length() == 1) {
               hex = '0' + hex;
           }
           System.out.print(hex.toUpperCase() + " ");
           strHex+=hex.toUpperCase() + " ";
       }
       System.out.println("");
       return strHex;
   }
	
}
