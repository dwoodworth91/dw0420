package model;

import java.time.Month;

public class DayMonthHolidayBean extends BaseHolidayBean {
	public DayMonthHolidayBean(String name, int ordinal, Month month) {
		super(name);
		this.ordinal = ordinal;
		this.month = month;
	}
	
	public String getName() {
		return name;
	}
	
	public int getOrdinal() {
		return ordinal;
	}

	public Month getMonth() {
		return month;
	}

	private String name;
	private int ordinal;
	private Month month;
}
