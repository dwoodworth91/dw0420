package model;

import java.time.DayOfWeek;
import java.time.Month;

public class DayMonthWeekdayHolidayBean extends DayMonthHolidayBean {
	public DayMonthWeekdayHolidayBean(String name, int ordinal, Month month, DayOfWeek weekday) {
		super(name, ordinal, month);
		this.weekday = weekday;
	}
	
	private DayOfWeek weekday;

	public DayOfWeek getWeekday() {
		return weekday;
	}
}
