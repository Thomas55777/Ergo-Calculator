package thomasWilliams.ErgoCalculator;

public class StringToMilliseconds {
	// using public or using no word makes the variable public
	private double millisecs;

	StringToMilliseconds(String StringInTimeFormat) {
		double t0 = 0;
		double tss = 0;
		double tmm = 0;

		if (StringInTimeFormat.indexOf(".") > 0
				&& StringInTimeFormat.indexOf(":") > 0) {
			t0 = Integer.parseInt(StringInTimeFormat.substring(
					StringInTimeFormat.indexOf(".") + 1,
					StringInTimeFormat.length()));
			tss = Integer.parseInt(StringInTimeFormat.substring(
					StringInTimeFormat.indexOf(":") + 1,
					StringInTimeFormat.indexOf(".")));
			tmm = Integer.parseInt(StringInTimeFormat.substring(0,
					StringInTimeFormat.indexOf(":")));
		} else if (StringInTimeFormat.indexOf(".") < 0
				&& StringInTimeFormat.indexOf(":") > 0) {
			t0 = 0;
			tss = Integer.parseInt(StringInTimeFormat.substring(
					StringInTimeFormat.indexOf(":") + 1,
					StringInTimeFormat.length()));
			tmm = Integer.parseInt(StringInTimeFormat.substring(0,
					StringInTimeFormat.indexOf(":")));
		} else if (StringInTimeFormat.indexOf(".") < 0
				&& StringInTimeFormat.indexOf(":") < 0) {
			t0 = 0;
			tss = 0;
			tmm = Integer.parseInt(StringInTimeFormat);
		}
		t0 = t0 / (10 * 60 * 60 * 24);
		tss = tss / (60 * 60 * 24);
		tmm = tmm / (60 * 24);

		millisecs = t0 + tss + tmm;
	}

	public double getMillisecs(String StringConvertedToMilliseconds) {
		//String StringInTimeFormat = this.StringInTimeFormat;
		System.out.println(StringConvertedToMilliseconds);
		System.out.println(millisecs);
		return millisecs;
	}
}	

