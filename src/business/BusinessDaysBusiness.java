package business;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import model.BaseHolidayBean;
import model.DayMonthHolidayBean;
import model.DayMonthWeekdayHolidayBean;
import service.HolidayService;
import util.time.DateUtils;

public class BusinessDaysBusiness {
	HolidayService holidayService = new HolidayService();
	
	private final Map<Integer, List<LocalDate>> observedHolidayDatesByYearCache = new HashMap<>();
	
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
			total += countHolidayOccurrencesInRange(start, end);
		}
		
		
		return total;
	}
	
	private long countHolidayOccurrencesInRange(LocalDate start, LocalDate end) {
		Stream<List<LocalDate>> holidaysForApplicableYears = IntStream.rangeClosed(start.getYear(), end.getYear())
			.mapToObj(this::getObservedHolidayDatesForYear);
		
		return holidaysForApplicableYears
			.mapToLong(dates -> countOrderedDateOccurrencesInRange(start, end, dates))
			.sum();
	}
	
	private long countOrderedDateOccurrencesInRange(LocalDate start, LocalDate end, List<LocalDate> orderedDates) {
		return orderedDates.stream()
			.dropWhile(date -> date.isBefore(start))
			.takeWhile(date -> !date.isAfter(end))
			.count();
	}
	
	private List<LocalDate> getObservedHolidayDatesForYear(Integer year) {
		return observedHolidayDatesByYearCache
			.computeIfAbsent(year, this::calculateAndSortAllHolidayObservanceDatesForYear);
	}
	
	private List<LocalDate> calculateAndSortAllHolidayObservanceDatesForYear(Integer year) {
		List<DayMonthHolidayBean> dayMonthHolidays = holidayService.getDayMonthHolidays();
		List<DayMonthWeekdayHolidayBean> dayMonthWeekdayHolidays = holidayService.getDayMonthWeekdayHolidays();
		
		DayMonthHolidayOccurrenceFinder dayMonthOccurrenceFinder =
			new DayMonthHolidayOccurrenceFinder();
		DayMonthWeekdayHolidayOccurrenceFinder dayMonthWeekdayOccurrenceFinder =
			new DayMonthWeekdayHolidayOccurrenceFinder();
		
		return Stream.of(
			calculateObservedDatesOfHolidayTypeForYear(dayMonthHolidays, dayMonthOccurrenceFinder, year),
			calculateObservedDatesOfHolidayTypeForYear(dayMonthWeekdayHolidays, dayMonthWeekdayOccurrenceFinder, year)
		).flatMap(it -> it).sorted().collect(Collectors.toList());
	}
	
	private <T extends BaseHolidayBean> Stream<LocalDate> calculateObservedDatesOfHolidayTypeForYear(
			List<T> holidays, IHolidayOccurrenceFinder<T> counter, int year) {
		return holidays.stream()
			.map(holiday -> counter.findOccurance(year, holiday));
	}
}
