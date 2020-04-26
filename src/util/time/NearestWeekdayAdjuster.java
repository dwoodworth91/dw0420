package util.time;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NearestWeekdayAdjuster implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate date = LocalDate.from(temporal);
		switch (date.getDayOfWeek()) {
			case SATURDAY:
				return date.minusDays(1);
			case SUNDAY:
				return date.plusDays(1);
			default:
				return temporal;
		}
	}

}
