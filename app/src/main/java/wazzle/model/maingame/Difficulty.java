package wazzle.model.maingame;

import java.util.Objects;

import com.google.gson.annotations.Expose;

public final class Difficulty {
	@Expose
	private final int lowerBound;
	
	@Expose
	private final int upperBound;
	
	@Expose
	private final long time;
	
	public Difficulty(final int lowerBound, final int upperBound, final long time) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.time = time;
	}
	
	public int getLowerBound() {
		return this.lowerBound;
	}
	
	public int getUpperBound() {
		return this.upperBound;
	}
	
	public long getTimeInMilliseconds() {
		return this.time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lowerBound, time, upperBound);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Difficulty other = (Difficulty) obj;
		return lowerBound == other.lowerBound && time == other.time && upperBound == other.upperBound;
	}

	@Override
	public String toString() {
		return "Difficulty [lowerBound=" + lowerBound + ", upperBound=" + upperBound + ", time=" + time + "]";
	}
}
