package model;

public class ToolTypeBean {
  private Long id;
  private String description;
  private long dailyCharge;
  private boolean accruesOnWeekDays;
  private boolean accruesOnWeekends;
  private boolean accruesOnHolidays;

  public ToolTypeBean(Long id, String description, long dailyCharge, boolean accruesOnWeekDays,
    boolean accruesOnWeekends, boolean accruesOnHolidays) {
    this.id = id;
    this.description = description;
    this.dailyCharge = dailyCharge;
    this.accruesOnWeekDays = accruesOnWeekDays;
    this.accruesOnWeekends = accruesOnWeekends;
    this.accruesOnHolidays = accruesOnHolidays;
  }

  public long getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public long getDailyCharge() {
    return this.dailyCharge;
  }

  public boolean getAccruesOnWeekDays() {
    return this.accruesOnWeekDays;
  }

  public boolean getAccruesOnWeekends() {
    return this.accruesOnWeekends;
  }

  public boolean getAccruesOnHolidays() {
    return this.accruesOnHolidays;
  }
}
