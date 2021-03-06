package business;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import model.DayMonthWeekdayHolidayBean;

public class DayMonthWeekdayHolidayOccurrenceFinder implements IHolidayOccurrenceFinder<DayMonthWeekdayHolidayBean> {

	@Override
	public LocalDate findOccurance(int year, DayMonthWeekdayHolidayBean holiday) {
		TemporalAdjuster nthWeekdayOfMonthAdjuster =
				TemporalAdjusters.dayOfWeekInMonth(holiday.getOrdinal(), holiday.getWeekday());
		return YearMonth
			.of(year, holiday.getMonth())
			.atDay(1)
			.with(nthWeekdayOfMonthAdjuster);
	}

}
