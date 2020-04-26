package util.currency;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {
	public static BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	public static String formatDollarsStringFromCents(long centsInt) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		BigDecimal cents = new BigDecimal(centsInt);
		return formatter.format(cents.divide(ONE_HUNDRED));
	}
}