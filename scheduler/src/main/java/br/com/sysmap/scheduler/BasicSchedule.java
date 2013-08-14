package br.com.sysmap.scheduler;

import org.joda.time.DateTime;

public abstract class BasicSchedule implements Schedule {

	private int every;

	private DateTime last;

	private DateTime begin;

	private Integer numberOfOcurrences;

	public DateTime endDate;

	@Override
	public DateTime showNext(DateTime last) {
		if (last == null) {
			throw new IllegalArgumentException("last cannot be null");
		}
		DateTime aux = internalNext(last);

		if (getEndKind() != EndKind.NoEndDate) {
			if (aux.compareTo(endDate) >= 0)
				aux = null;
		}

		return aux;
	}

	protected abstract DateTime internalNext(DateTime last);

	public DateTime next() {
		if (last != null) {
			last = showNext(last);
		} else {
			last = showNext(begin);
		}

		return last;
	}

	public int getEvery() {
		return every;
	}

	public void setEvery(int every) {
		this.every = every;
	}

	public DateTime getLast() {
		return last;
	}

	public void setLast(DateTime last) {
		this.last = last;
	}

	public DateTime getBegin() {
		return begin;
	}

	public void setBegin(DateTime begin) {
		this.begin = begin;
	}

	public EndKind getEndKind() {
		if (endDate == null) {
			if (numberOfOcurrences == null) {
				return EndKind.NoEndDate;
			} else {
				return EndKind.NumberOfOcurrences;
			}
		} else {
			if (numberOfOcurrences == null) {
				return EndKind.EndDate;
			} else {
				throw new IllegalStateException("numberOfOcurrences and endDate cannot be both not null");
			}
		}

	}

	public Integer getNumberOfOcurrences() {
		return numberOfOcurrences;
	}

	public void setNumberOfOcurrences(Integer numberOfOcurrences) {
		this.numberOfOcurrences = numberOfOcurrences;
		if(numberOfOcurrences!=null)
			endDate=null;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
		if(endDate!=null){
			numberOfOcurrences=null;
		}
	}

}
