package wazzle.model.maingame;

public enum DifficultyNames {
	EASY("EASY"),
	HARD("HARD");
	
	private final String name;
	
	DifficultyNames(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
