package br.com.sysmap.scheduler;

import org.joda.time.DateTime;

public interface Schedule {
	DateTime showNext(DateTime last);
}
