package Proj;
import java.time.LocalDateTime;

public class Reservation_system {
	private String Top_Num;
	private String Bottom_Num;
	private String Num;
	
	public void set_TopNum(String n, String i) {
		int r = 0;
		String Top_Num;
		
		if (n.equals("V")) {
            r = 1;
        } else if (n.equals("S")) {
            r = 2;
        } else if (n.equals("A")) {
            r = 3;
        } else if (n.equals("B")) {
            r = 4;
        }
		
		String rs = Integer.toString(r);
		Top_Num = rs + i;
		
		this.Top_Num = Top_Num;
    }
	
	public void set_BottomNum() {
		LocalDateTime Time = LocalDateTime.now();
		
        int d = Time.getDayOfMonth();
        int h = Time.getHour();
        int m = Time.getMinute();
        int s = Time.getSecond();
        
        int t = (d * 1000000) + (h * 10000) + (m * 100) + s ;
       
        this.Bottom_Num = Integer.toString(t);
	}
	
	public String get_Num (){
		
		return this.Num = this.Top_Num + this.Bottom_Num;
	}
	
	
	
}
