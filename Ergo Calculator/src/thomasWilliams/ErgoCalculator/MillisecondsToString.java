package thomasWilliams.ErgoCalculator;

public class MillisecondsToString {
	private String ConvertedMilliseconds;

	MillisecondsToString(double tempNumber) {
		double t0 = 0;
		double tss = 0;
		double tmm = 0;
		String stmm;
		String stss;
		String st0;

		tmm = (int) (tempNumber * 60 * 24);
		tss = (int) ((tempNumber - (tmm / (60 * 24))) * 60 * 60 * 24);
		t0 = (int) (((tempNumber - ((tss / (60 * 60 * 24)) + (tmm / (60 * 24)))) * 10 * 60 * 60 * 24));
		stmm = Integer.toString((int) tmm);
		stss = Integer.toString((int) tss);
		st0 = Integer.toString((int) t0);

		if (tempNumber > (99.0 / (60 * 24)) + (59.0 / (60 * 60 * 24))
				+ (9.0 / (10 * 60 * 60 * 24))) {
			ConvertedMilliseconds = "99:59.9";
		} else {
			if (stmm.length() < 2) {
				stmm = "0" + stmm;
			}
			if (stss.length() < 2) {
				stss = "0" + stss;
			}

			ConvertedMilliseconds = stmm + ":" + stss + "." + st0;
		}
		System.out.println(tempNumber);
		System.out.println(stmm + " | " + stss + " | " + st0);
	}

	public String getConvertedString() {
		return ConvertedMilliseconds;
	}
}