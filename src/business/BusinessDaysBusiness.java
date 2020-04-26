package business;

import java.time.LocalDate;
import java.util.List;

import model.BaseHolidayBean;
import model.DayMonthHolidayBean;
import model.DayMonthWeekdayHolidayBean;
import service.HolidayService;
import util.time.DateUtils;

public class BusinessDaysBusiness {
	HolidayService holidayService = new HolidayService();
	
	public long countNonChargeDaysInRange(
			boolean includeWeekdays, boolean includeWeekends, boolean includeHolidays, LocalDate start, LocalDate end) {
		long total = 0;
		
		if (includeWeekdays) {
			total += DateUtils.countWeekdays(start, end);
		}
		
		if (includeWeekends) {
			total += DateUtils.countWeekends(start, end);
		}

		if (includeHolidays) {
			total += getHolidayOccurrencesOfAllTypesInRange(start, end);
		}
		
		
		return total;
	}
	
	private long getHolidayOccurrencesOfAllTypesInRange(LocalDate start, LocalDate end) {
		List<DayMonthHolidayBean> dayMonthHolidays = holidayService.getDayMonthHolidays();
		List<DayMonthWeekdayHolidayBean> dayMonthWeekdayHolidays = holidayService.getDayMonthWeekdayHolidays();
		
		DayMonthHolidayOccurrenceCounter dayMonthOccurrenceCounter = new DayMonthHolidayOccurrenceCounter();
		DayMonthWeekdayHolidayOccurrenceCounter dayMonthWeekdayOccurrenceCounter = new DayMonthWeekdayHolidayOccurrenceCounter();
		
		return
				countHolidaysOfTypeInRange(dayMonthHolidays, dayMonthOccurrenceCounter, start, end) +
				countHolidaysOfTypeInRange(dayMonthWeekdayHolidays, dayMonthWeekdayOccurrenceCounter, start, end);
	}
	
	private <T extends BaseHolidayBean> long countHolidaysOfTypeInRange(
			List<T> holidays, BaseHolidayOccurrenceCounter<T> counter, LocalDate start, LocalDate end) {
		return holidays.stream()
			.mapToLong(holiday -> counter.countOccurancesInRange(holiday, start, end))
			.sum();
	}
}
