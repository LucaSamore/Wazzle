package wazzle.model.maingame;

import java.util.Objects;

import com.google.gson.annotations.Expose;

public final class Difficulty {
	
	@Expose
	private final String difficultyName;
	
	@Expose
	private final int gridShape;
	
	@Expose
	private final int lowerBound;
	
	@Expose
	private final int upperBound;
	
	@Expose
	private final long time;
	
	public Difficulty(final String difficultyName, final int gridShape, final int lowerBound, final int upperBound, final long time) {
		this.difficultyName = difficultyName;
		this.gridShape = gridShape;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.time = time;
	}
	
	public String getDifficultyName() {
		return this.difficultyName;
	}
	
	public int getGridShape() {
		return this.gridShape;
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
	
	public static Difficulty getDefault() {
		return new Difficulty(DifficultyNames.EASY.getName(), 4, 76, 200, 240000L);
	}

	@Override
	public int hashCode() {
		return Objects.hash(difficultyName, gridShape, lowerBound, time, upperBound);
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
		return Objects.equals(difficultyName, other.difficultyName) && gridShape == other.gridShape
				&& lowerBound == other.lowerBound && time == other.time && upperBound == other.upperBound;
	}

	@Override
	public String toString() {
		return "Difficulty [difficultyName=" + difficultyName + ", gridShape=" + gridShape + ", lowerBound="
				+ lowerBound + ", upperBound=" + upperBound + ", time=" + time + "]";
	}
}
