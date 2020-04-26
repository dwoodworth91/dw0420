package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.currency.CurrencyUtils;

public class RentalAgreementBean {
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private long rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private long dailyRentalCharge;
	private long chargeDays;
	private long preDiscountCharge;
	private int discountPercent;
	private long discountAmount;
	private long finalCharge;
	
	public RentalAgreementBean(String toolCode, String toolType, String toolBrand, long rentalDays,
			LocalDate checkoutDate, LocalDate dueDate, long dailyRentalCharge, long chargeDays, long prediscount,
			int discountPercent, long discountAmount, long finalCharge) {
		super();
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.toolBrand = toolBrand;
		this.rentalDays = rentalDays;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.dailyRentalCharge = dailyRentalCharge;
		this.chargeDays = chargeDays;
		this.preDiscountCharge = prediscount;
		this.discountPercent = discountPercent;
		this.discountAmount = discountAmount;
		this.finalCharge = finalCharge;
	}

	public String getToolCode() {
		return toolCode;
	}

	public String getToolType() {
		return toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public long getRentalDays() {
		return rentalDays;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public long getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public long getChargeDays() {
		return chargeDays;
	}

	public long getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public long getDiscountAmount() {
		return discountAmount;
	}

	public long getFinalCharge() {
		return finalCharge;
	}

	@Override
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		String fieldAndLabelSeparator = ": ";
		
		return Stream.of(
			Map.entry("Tool code", toolCode),
			Map.entry("Tool type", toolType),
			Map.entry("Tool brand", toolBrand),
			Map.entry("Rental days", String.valueOf(rentalDays)),
			Map.entry("Check out date", checkoutDate.format(dateFormatter)),
			Map.entry("Due date", dueDate.format(dateFormatter)),
			Map.entry("Daily rental charge", CurrencyUtils.formatDollarsStringFromCents(dailyRentalCharge)),
			Map.entry("Charge days", String.valueOf(chargeDays)),
			Map.entry("Pre-discount charge", CurrencyUtils.formatDollarsStringFromCents(preDiscountCharge)),
			Map.entry("Discount percent", String.valueOf(discountPercent) + "%"),
			Map.entry("Discount amount", CurrencyUtils.formatDollarsStringFromCents(discountAmount)),
			Map.entry("Final charge", CurrencyUtils.formatDollarsStringFromCents(finalCharge))
		)
			.map(it -> String.join(fieldAndLabelSeparator, it.getKey(), it.getValue()))
			.collect(Collectors.joining("\n"));
	}
}
