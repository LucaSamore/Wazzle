package wazzle.model.maingame;

public enum Range {
	
	HIGH_FREQUENCY(3),
	
	MEDIUM_FREQUENCY(2),
	
	LOW_FREQUENCY(1);
	
	int weight;
	
	Range(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
}
