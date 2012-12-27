package com.nacnez.util.microbenchmarktool.reporter.format;

import java.text.DecimalFormat;

public class FormatUtil {

	public static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}

}
