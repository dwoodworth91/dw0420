package business;

import java.time.LocalDate;
import java.util.stream.IntStream;

import model.BaseHolidayBean;

public abstract class BaseHolidayOccurrenceCounter<T extends BaseHolidayBean> {
	abstract protected LocalDate findOccurance(int year, T holiday);
	
	public long countOccurancesInRange(T holiday, LocalDate start, LocalDate end) {
  	return IntStream.rangeClosed(start.getYear(), end.getYear())
  			.mapToObj(year -> this.findOccurance(year, holiday))
  			.filter(holidayOccurrence -> !(holidayOccurrence.isBefore(start) || holidayOccurrence.isAfter(end)))
  			.count();
  }
}