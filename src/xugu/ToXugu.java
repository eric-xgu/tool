package xugu;
import java.io.File;
import java.io.FileInputStream;


public class ToXugu {
	public static  void main(String[] args) {
		try {
		File f= new File("d:/a");
		System.out.println(f.length());
		FileInputStream fis=new FileInputStream(f);		
		byte[] b=new byte[24];
		int bytes_count=0;
		int beg=0;
		int end=0;
		int head_d;
		int read_switch=1; //读取开关
		int end_label=0;	//结束标志
		int read;
		while((read=fis.read(b))!=-1) {
			
			System.out.println();
		}
		
		
		
		System.out.println();
		
		//Util.getHex(b);
		/*
		while(true) {
			if (read_switch==1) {
				end=4;
				bytes_count+=end;
				System.out.println(beg);
				end_label=fis.read(head_b);
				
				if (end_label==-1) {
					break;
				}
				head_d=Util.byteArrayToInt(head_b);
				System.out.println(head_d);
				Util.getHex(head_b);
				end=head_d;
				beg=bytes_count;
				//System.out.println(bytes_count);
				read_switch=0;
			}
			else{
				
				end_label=fis.read(b,beg,end); //数据
				bytes_count+=end;		//加上读取数据的字节
				beg=bytes_count;
				read_switch=1;
				Util.getHex(b);
				//System.out.println(bytes_count);
			}
			
		}*/
		//Util.getHex(b);
	
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
