package br.com.sysmap.scheduler;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

public class EasterScheduleTest {

	@Test
	public void showNext() {
		EasterSchedule schedule = new EasterSchedule();
		DateTime last = new DateTime(2000,1,1,0,0,0);
		DateTime next = schedule.showNext(last );
		assert new DateTime(2000,4,23,0,0,0).equals(next);
	}

	@Test(expectedExceptions= RuntimeException.class)
	public void showNextNull() {
		final EasterSchedule schedule = new EasterSchedule();
		schedule.showNext(null );
	}

	@Test
	public void noEndDateInicial() {
		EasterSchedule schedule = new EasterSchedule();
		assert schedule.getEndDate() == null;
		assert schedule.getNumberOfOcurrences() == null;
		assert schedule.getEndKind() == EndKind.NoEndDate;
	}

	@Test
	public void endDate() {
		EasterSchedule schedule = new EasterSchedule();
		DateTime expect = new DateTime(2000,1,1,0,0,0);
		schedule.setEndDate(expect);
		assert schedule.getEndDate().equals(expect);
		assert schedule.getNumberOfOcurrences() == null;
		assert schedule.getEndKind() == EndKind.EndDate;
	}
	
	@Test
	public void numberOfOcurrences() {
		EasterSchedule schedule = new EasterSchedule();
		schedule.setNumberOfOcurrences(10);
		assert schedule.getEndDate() == null;
		assert schedule.getNumberOfOcurrences() == 10;
		assert schedule.getEndKind() == EndKind.NumberOfOcurrences;
	}

	@Test
	public void tryInvalidEndKindNullNumber() {
		EasterSchedule schedule = new EasterSchedule();
		schedule.setEndDate(new DateTime());
		schedule.setNumberOfOcurrences(10);
		schedule.setNumberOfOcurrences(null);
		assert schedule.getEndDate() == null;
		assert schedule.getNumberOfOcurrences() == null;
		assert schedule.getEndKind() == EndKind.NoEndDate;
	}

	@Test
	public void tryInvalidEndKindNullEndDate() {
		EasterSchedule schedule = new EasterSchedule();
		schedule.setNumberOfOcurrences(10);
		schedule.setEndDate(new DateTime());
		schedule.setEndDate(null);
		assert schedule.getEndDate() == null;
		assert schedule.getNumberOfOcurrences() == null;
		assert schedule.getEndKind() == EndKind.NoEndDate;
	}
}
