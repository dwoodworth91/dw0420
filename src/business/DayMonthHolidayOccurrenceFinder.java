package business;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

import model.DayMonthHolidayBean;
import util.time.NearestWeekdayAdjuster;

public class DayMonthHolidayOccurrenceFinder implements IHolidayOccurrenceFinder<DayMonthHolidayBean> {
	
	private static TemporalAdjuster nearestWeekdayAdjuster = new NearestWeekdayAdjuster();

	@Override
	public LocalDate findOccurance(int year, DayMonthHolidayBean holiday) {
		return LocalDate.of(year, holiday.getMonth(), holiday.getOrdinal())
			.with(nearestWeekdayAdjuster);
	}

}
