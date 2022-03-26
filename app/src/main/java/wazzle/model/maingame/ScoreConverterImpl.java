package wazzle.model.maingame;

import java.util.function.BinaryOperator;

public final class ScoreMapperImpl implements ScoreMapper {
	
	private static final double MAX_SCORE = 5.0;
	private final WeightedAlphabet weightedAlphabet;
	private final ScoreAdapter adapter;
	private final BinaryOperator<Double> mapper = (value, max) -> (MAX_SCORE * value) / max;
	
	public ScoreMapperImpl(final WeightedAlphabet weightedAlphabet) {
		this.weightedAlphabet = weightedAlphabet;
		this.adapter = new ScoreAdapter(this.weightedAlphabet, this.mapper);
	}
			
	@Override
	public WeightedAlphabet convert() {
		return new WeightedAlphabetImpl(this.adapter.getWeightedAlphabet());
	}

}
