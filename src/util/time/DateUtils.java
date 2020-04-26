package util.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.function.Predicate;

public class DateUtils {
	private static final EnumSet<DayOfWeek> weekends = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
	private static final Predicate<LocalDate> isWeekend = date -> weekends.contains(date.getDayOfWeek());
	private static final Predicate<LocalDate> isNotWeekend = isWeekend.negate();
	
	public static long countWeekdays(LocalDate start, LocalDate end) {
		return countMatchingDaysInInclusiveRange(start, end, isNotWeekend);
	}
	
	public static long countWeekends(LocalDate start, LocalDate end) {
		return countMatchingDaysInInclusiveRange(start, end, isWeekend);
	}
	
	private static long countMatchingDaysInInclusiveRange(LocalDate start, LocalDate end, Predicate<LocalDate> predicate) {
		return start.datesUntil(end.plusDays(1)).filter(predicate).count();
	}
}
