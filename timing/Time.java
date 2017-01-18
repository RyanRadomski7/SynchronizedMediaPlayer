public class Time {
	private int hour;
	private int minute;
	private int second;
	private int millisecond;
	
	public Time(int h, int min, int s, int mil) {
		this.hour = h;
		this.minute = min;
		this.second = s;
		this.millisecond = mil;
	}
	
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public int getMillisecond() {
		return millisecond;
	}
	
	public String toString() {
		return "Time [hour=" + hour + ", minute=" + minute + ", second="
				+ second + ", millisecond=" + millisecond + "]";
	}
}
