import java.util.Calendar;
public class TimeServer {
	public TimeServer() {
	} 
	
	public Time getTime() {
		Calendar rightNow = Calendar.getInstance();

		// offset to add since we're not UTC
		long offset = rightNow.get(Calendar.ZONE_OFFSET) +
		    rightNow.get(Calendar.DST_OFFSET);
		long sinceMidnight = (rightNow.getTimeInMillis() + offset) %
		    (24 * 60 * 60 * 1000);

		long secondsSinceMidnight = sinceMidnight / 1000;
		long minutesSinceMidnight = secondsSinceMidnight / 60;
		long hoursSinceMidnight = minutesSinceMidnight / 60;
		
		long currentHour = hoursSinceMidnight;
		long currentMinute = minutesSinceMidnight - (hoursSinceMidnight * 60);
		long currentSecond = secondsSinceMidnight - (minutesSinceMidnight * 60);
		long currentMilliSecond = sinceMidnight - (secondsSinceMidnight * 1000);

		return new Time((int)currentHour, (int)currentMinute, (int)currentSecond, (int)currentMilliSecond);
	}
	
	public void waitOnMinute() {
		Time t = getTime();
		int millisToChange = 1000 - t.getMillisecond();
		int secondsToChange = 59 - t.getSecond();
		
		try {
			Thread.sleep(millisToChange + secondsToChange * 1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
