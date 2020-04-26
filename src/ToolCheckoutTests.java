import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import business.ToolBusiness;
import static business.ToolBusiness.INVALID_DISCOUNT_PERCENT_MSG;
import static business.ToolBusiness.INVALID_RENTAL_DURATION_MSG;
import model.RentalAgreementBean;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ToolCheckoutTests {
	private String toolCode;
	private long rentalDayCount;
	private int discountPercent;
	private LocalDate checkoutDate;
	
	private String expectedExceptionMsg;
	private Long expectedChargeDays;
	private Long expectedPreDiscountAmount;
	private Long expectedDiscountAmount;
	
	
	private ToolBusiness toolBusiness;
	
	public ToolCheckoutTests(String toolCode, long rentalDayCount, int discountPercent, LocalDate checkoutDate,
			String expectedExceptionMsg, Long expectedChargeDays, Long expectedPreDiscountAmount,
			Long expectedDiscountAmount) {
		super();
		this.toolCode = toolCode;
		this.rentalDayCount = rentalDayCount;
		this.discountPercent = discountPercent;
		this.checkoutDate = checkoutDate;
		
		this.expectedExceptionMsg = expectedExceptionMsg;
		this.expectedChargeDays = expectedChargeDays;
		this.expectedPreDiscountAmount = expectedPreDiscountAmount;
		this.expectedDiscountAmount = expectedDiscountAmount;
	}

	@Before
	public void initialize() {
		toolBusiness = new ToolBusiness();
	}
	
	//All test cases as per requirements unless otherwise indicated
	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {
			{ "JAKR", 5l, 101, LocalDate.of(2015, Month.MARCH, 3), INVALID_DISCOUNT_PERCENT_MSG, null, null, null },
			//Added to test invalid rental days case
			{ "JAKR", 0L, 0, LocalDate.of(2015, Month.MARCH, 3), INVALID_RENTAL_DURATION_MSG, null, null, null },
			{ "LADW", 3l, 10, LocalDate.of(2020, Month.JULY, 2), null, 2L, 398L, 40L },
			{ "CHNS", 5l, 25, LocalDate.of(2015, Month.JULY, 2), null, 3L, 447L, 112L },
			{ "JAKD", 6l, 0, LocalDate.of(2015, Month.SEPTEMBER, 3), null, 3L, 897L, 0L },
			{ "JAKR", 9l, 0, LocalDate.of(2015, Month.JULY, 2), null, 5L, 1495L, 0L },
			{ "JAKR", 4l, 50, LocalDate.of(2020, Month.JULY, 2), null, 1L, 299L, 150L },
			//Added to test year straddling rental case
			{ "JAKR", 5l, 50, LocalDate.of(2020, Month.DECEMBER, 30), null, 3L, 897L, 449L },
		});
	}
	
	@Test
	public void testRentalAgreement() {
		if (expectedExceptionMsg != null) {
			Exception exception = assertThrows(RuntimeException.class, () -> {
				toolBusiness.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);
	    });
			assertTrue(exception.getMessage().equals(expectedExceptionMsg));
		} else {
			RentalAgreementBean result = toolBusiness.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);
			
			if (expectedChargeDays != null) {
				assertTrue(expectedChargeDays.equals(result.getChargeDays()));
			}
			
			if (expectedPreDiscountAmount != null) {
				assertTrue(expectedPreDiscountAmount.equals(result.getPreDiscountCharge()));
			}
			
			if (expectedDiscountAmount != null) {
				assertTrue(expectedDiscountAmount.equals(result.getDiscountAmount()));
			}
		}
	}
}
