package wazzle.model.maingame;

import javafx.util.Pair;

public class LetterImpl implements Letter {
	
	private char content;
	private Pair<Integer, Integer> position;
	
	public LetterImpl(char content, Pair<Integer, Integer> position) {
		this.content = content;
		this.position = new Pair<>(position.getKey(), position.getValue());
	}

	public Character getContent() {
		return this.content;
	}

	public Pair<Integer, Integer> getPosition() {
		return this.position;
	}

	@Override
	public String toString() {
		return "LetterImpl [content=" + content + ", position=" + position + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + content;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		return true;
	}

}
