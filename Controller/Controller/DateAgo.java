package Controller;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAgo {
	public static String formatDate(java.sql.Timestamp datein) {		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");		
		
		String dateStart = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(datein);
		String dateStop = formatter.format(date);

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
		d1 = format.parse(dateStart);
		d2 = format.parse(dateStop);

		//in milliseconds
		long diff = d2.getTime() - d1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		long diffYears = diffDays / 365;
		
		if(diffYears > 0) 
			return diffYears+" year ago";
		if(diffDays > 0) 
			return diffDays+" day ago";
		if(diffHours > 0) 
			return diffHours+" hour ago";
		if(diffMinutes > 0) 
			return diffMinutes+" minutes ago";
		if(diffSeconds >= 0) 
			return diffSeconds+" second ago";

		} catch (Exception e) {		
			e.printStackTrace();			
		}
		return null;
	}

}
