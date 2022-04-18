package wazzle.model.minigame;

public enum Result {
	
	CORRECT("45e521"),

	CORRECT_WRONG_PLACE("ffff00"),
	
	WRONG("");
	
	private String color;
	
	private Result(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
}
