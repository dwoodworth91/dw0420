package service;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import model.DayMonthHolidayBean;
import model.DayMonthWeekdayHolidayBean;

public class HolidayService {
	//Dummy data that would come from the DB IRL...
	private static List<DayMonthHolidayBean> dayMonthHolidays =
		Arrays.asList(
			new DayMonthHolidayBean("The 4th of July", 4, Month.JULY)
		);
	
	private static List<DayMonthWeekdayHolidayBean> dayMonthWeekdayHolidays =
		Arrays.asList(
			new DayMonthWeekdayHolidayBean("Labor Day", 1, Month.SEPTEMBER, DayOfWeek.MONDAY)
		);
	//End;
	
	@SuppressWarnings("static-access")
	public List<DayMonthHolidayBean> getDayMonthHolidays() {
		return this.dayMonthHolidays;
	}
	
	@SuppressWarnings("static-access")
	public List<DayMonthWeekdayHolidayBean> getDayMonthWeekdayHolidays() {
		return this.dayMonthWeekdayHolidays;
	}
}
