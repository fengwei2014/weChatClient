package slp.tt.ui.web.test;

import java.util.Calendar;


public class Historytest{
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.HOUR, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.out.println(cal.getTimeInMillis());
		
		System.out.println(System.currentTimeMillis());
		
	}
	
}
