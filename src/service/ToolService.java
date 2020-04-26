package service;

import model.ToolBean;
import model.ToolTypeBean;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;

public class ToolService {
	//Dummy data that would come from the DB IRL...
  private static Map<Long, ToolTypeBean> toolTypesById = Stream.of(
    new ToolTypeBean(1L, "Ladder", 199, true, true, false),
    new ToolTypeBean(2L, "Chainsaw", 149, true, false, true),
    new ToolTypeBean(3L, "Jackhammer", 299, true, false, false)
  ).collect(Collectors.toMap(ToolTypeBean::getId, Function.identity()));

  private static Map<String, ToolBean> toolsByToolCode = Stream.of(
    new ToolBean("LADW", "Werner", toolTypesById.get(1L)),
    new ToolBean("CHNS", "Stihl", toolTypesById.get(2L)),
    new ToolBean("JAKR", "Ridgid", toolTypesById.get(3L)),
    new ToolBean("JAKD", "DeWalt", toolTypesById.get(3L))
  ).collect(Collectors.toMap(ToolBean::getCode, Function.identity()));
  //End;

  @SuppressWarnings("static-access")
	public ToolBean get(String toolCode) {
    return this.toolsByToolCode.get(toolCode);
  }
}
