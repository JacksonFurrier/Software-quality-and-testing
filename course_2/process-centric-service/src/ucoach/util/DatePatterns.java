package ucoach.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
import java.util.Date;
import ucoach.util.ICalendar;

public class DatePatterns {

	//CASE 1. Extract an interface
	public static Date getYesterdayDate(){
		ICalendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	//CASE END

	//CASE 2. Use delegates
	public static interface ICalendarGetInstance {
		public Calendar getDelegatedClass();
	}

	public static Date getYesterdayDate_Delegate(){
		Delegator delegator = new Delegator(ICalendarGetInstance.class);

		ICalendarGetInstance[] objects = new ICalendarGetInstance[1];

		objects[0] = (ICalendarGetInstance) delegator.build(Calendar.class, "getInstance");

		ICalendarGetInstance item = objects[0];
		Calendar cal = item.getDelegatedClass();

		return cal.getTime();
	}
	// CASE END

	//CASE 3. Use factory methods when doing dependency injection
	public static Date getYesterdayDate_Factory(){
		return ManagerFactory.Create().getTime();
	}
	//CASE END

	public static String dateFormater(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}


	
	public static Date dateParser(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}


