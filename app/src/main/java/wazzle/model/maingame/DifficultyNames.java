package wazzle.model.maingame;

/**
 * This enum groups all the {@link wazzle.model.maingame.Difficulty} names
 * and provides a method for obtaining them.
 */
public enum DifficultyNames {
	
	EASY("EASY"),
	
	HARD("HARD");
	
	private final String name;
	
	/**
	 * Construct a new DifficultyNames object
	 * @param name a {@code String} representing the given name.
	 */
	DifficultyNames(final String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name.
	 * @return a {@code String} representing the name.
	 */
	public String getName() {
		return this.name;
	}
}
