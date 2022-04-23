package wazzle.model.maingame;

import javafx.util.Pair;

/**
 * This class is a concrete implementation for {@link Letter}.
 * It provides methods getting letters attributes.
 */
public final class LetterImpl implements Letter {
	
	private char content;
	private Pair<Integer, Integer> position;
	private int score;
	
	/**
	 * Construct a new Letter. 
	 * 
	 * @param position The position of the Letter.
	 * @param content The content of the Letter.
	 * @param score	The score of the Letter.
	 */
	public LetterImpl (final Pair<Integer, Integer> position, final char content, final int score) {
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
	public int getScore() {
		return score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + content;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + score;
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
		if (score != other.score)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LetterImpl [content=" + content + ", position=" + position + ", score=" + score + "]";
	}

}
