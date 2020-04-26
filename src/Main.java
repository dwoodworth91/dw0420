import java.time.LocalDate;
import java.time.Month;

import business.ToolBusiness;
import model.RentalAgreementBean;

public class Main {
	public static void main(String []args) {
		ToolBusiness toolBusiness = new ToolBusiness();
	
		RentalAgreementBean agreement =
				toolBusiness.checkout("JAKR", 5l, 50, LocalDate.of(2020, Month.DECEMBER, 30));
		
    System.out.println(agreement.toString());
  }
}

