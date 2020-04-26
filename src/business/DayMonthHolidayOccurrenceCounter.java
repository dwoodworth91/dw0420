package business;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

import model.DayMonthHolidayBean;
import util.time.NearestWeekdayAdjuster;

public class DayMonthHolidayOccurrenceCounter extends BaseHolidayOccurrenceCounter<DayMonthHolidayBean> {
	
	private static TemporalAdjuster nearestWeekdayAdjuster = new NearestWeekdayAdjuster();

	@Override
	protected LocalDate findOccurance(int year, DayMonthHolidayBean holiday) {
		return LocalDate.of(year, holiday.getMonth(), holiday.getOrdinal())
			.with(nearestWeekdayAdjuster);
	}

}
