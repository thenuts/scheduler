package br.com.sysmap.scheduler;

import org.joda.time.DateTime;

public class EasterSchedule extends BasicSchedule {
	private int daysFromEaster;

	@Override
	protected DateTime internalNext(DateTime last) {
		if (last == null) {
			throw new IllegalArgumentException("last cannot be null");
		}
		DateTime Result = CalculateEaster(last.getYear());

		if (last.compareTo(Result) >= 0)
			Result = CalculateEaster(last.getYear() + 1);

		return Result;
	}

	private DateTime CalculateEaster(int year) {
		int a = year % 19;
		int b = year / 100;
		int c = (3 * b - 5) / 4;
		int d = ((12 + 11 * a + (8 * b + 13) / 25 - c) % 30 + 30) % 30;
		int e;
		if ((d == 0) || (d == 1 && a > 10))
			e = 56 - d;
		else
			e = 57 - d;

		int days = e - (e + (5 * year) / 4 - c) % 7;

		DateTime Result = (new DateTime(year, 3, 1, 0, 0, 0))
				.plusDays(days - 1);
		Result.plusDays(daysFromEaster);
		return Result;
	}

	public int getDaysFromEaster() {
		return daysFromEaster;
	}

	public void setDaysFromEaster(int daysFromEaster) {
		this.daysFromEaster = daysFromEaster;
	}
}
