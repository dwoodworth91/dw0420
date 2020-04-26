package business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import model.RentalAgreementBean;
import model.ToolBean;
import service.ToolService;
import util.currency.CurrencyUtils;

public class ToolBusiness {
	public static final String INVALID_DISCOUNT_PERCENT_MSG =
			"The discount amount should be provided as a whole number between 1 and 100";
	public static final String INVALID_RENTAL_DURATION_MSG = "The duration of a rental must be at least one day";
	
	ToolService toolService = new ToolService();
	BusinessDaysBusiness businessDaysBusiness = new BusinessDaysBusiness();
	
	public RentalAgreementBean checkout(String toolCode, long rentalDayCount, int discountPercent, LocalDate checkoutDate) {
		assertRentalDayCountIsValid(rentalDayCount);
		assertDiscountPercentIsValid(discountPercent);
		
		LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);
		ToolBean tool = toolService.get(toolCode);
		
		long chargeDays = rentalDayCount - businessDaysBusiness.countNonChargeDaysInRange(
    		!tool.getType().getAccruesOnWeekDays(),
    		!tool.getType().getAccruesOnWeekends(),
    		!tool.getType().getAccruesOnHolidays(),
    		checkoutDate, dueDate);
		
		long preDiscountCharge = chargeDays * tool.getType().getDailyCharge();
		long discountAmount = calculateDiscount(discountPercent, preDiscountCharge);
		long finalCharge = preDiscountCharge - discountAmount;
		
		return new RentalAgreementBean(
			toolCode,
			tool.getType().getDescription(),
			tool.getBrand(),
			rentalDayCount,
			checkoutDate,
			dueDate,
			tool.getType().getDailyCharge(),
			chargeDays,
			preDiscountCharge,
			discountPercent,
			discountAmount,
			finalCharge
		);
	}
	
	private static void assertRentalDayCountIsValid(long count) {
		if (count < 1) {
			throw new RuntimeException(INVALID_RENTAL_DURATION_MSG);
		}
	}
	
	private static void assertDiscountPercentIsValid(int discount) {
		if (discount < 0 || discount > 100) {
			throw new RuntimeException(INVALID_DISCOUNT_PERCENT_MSG);
		}
	}
	
	private long calculateDiscount(int discountPercentInteger, long totalInteger) {
		BigDecimal discountPercent = new BigDecimal(discountPercentInteger);
		BigDecimal total = new BigDecimal(totalInteger);
		return total.multiply(discountPercent).divide(CurrencyUtils.ONE_HUNDRED, RoundingMode.HALF_UP).longValueExact();
	}
}
