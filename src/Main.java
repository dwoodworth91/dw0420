import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import business.ToolBusiness;
import model.RentalAgreementBean;

public class Main {
	public static void main(String []args) {
		ToolBusiness toolBusiness = new ToolBusiness();
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	
		int start = 5;
		int end = 10;
		String result = Stream.of(
			LocalDate.of(2015, Month.MARCH, 4),
			LocalDate.of(2020, Month.MARCH, 3),
			LocalDate.of(2015, Month.JULY, 2)
		).sorted().map(it -> it.format(dateFormatter))
				.collect(Collectors.joining(", "));
		
		
		
    System.out.println(result);
  }
}

