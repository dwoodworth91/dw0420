package model;

public abstract class BaseHolidayBean {
	public BaseHolidayBean(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	private String name;
}
