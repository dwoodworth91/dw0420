package model;

import model.ToolTypeBean;

public class ToolBean {
  private String code;
  private String brand;
  private ToolTypeBean type;

  public ToolBean(String code, String brand, ToolTypeBean type) {
    this.code = code;
    this.brand = brand;
    this.type = type;
  }

  public String getCode() {
    return this.code;
  }

  public String getBrand() {
    return this.brand;
  }

  public ToolTypeBean getType() {
    return this.type;
  }
}
