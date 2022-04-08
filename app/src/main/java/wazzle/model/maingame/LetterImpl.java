package wazzle.model.maingame;

import javafx.util.Pair;

public class LetterImpl implements Letter {
	
	private char content;
	private Pair<Integer, Integer> position;
	private double score;
	
	/**
	 * Construct a new Letter. 
	 * 
	 * @param position The position of the Letter.
	 * @param content The content of the Letter.
	 * @param score	The score of the Letter.
	 */
	public LetterImpl (final Pair<Integer, Integer> position, final char content, final double score) {
		this.content = content;
		this.position = new Pair<>(position.getKey(), position.getValue());
		this.score = score;
	}

	/**
	 * {@inheritDoc}
	 */
	public Pair<Integer, Integer> getPosition() {
		return this.position;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public char getContent() {
		return this.content;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "LetterImpl [content=" + content + ", position=" + position + ", score=" + score + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + content;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LetterImpl other = (LetterImpl) obj;
		if (content != other.content)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
			return false;
		return true;
	}

}
