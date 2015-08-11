package slp.tt.ui.web.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class TimeTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		System.out.println((int) (cal.getTimeInMillis()/1000));
	}
}
