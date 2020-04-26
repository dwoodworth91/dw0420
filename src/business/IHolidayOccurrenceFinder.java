package business;

import java.time.LocalDate;

import model.BaseHolidayBean;

public interface IHolidayOccurrenceFinder<T extends BaseHolidayBean> {
	public LocalDate findOccurance(int year, T holiday);
}