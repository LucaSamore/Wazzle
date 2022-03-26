package wazzle.model.maingame;

import java.util.function.BinaryOperator;

public final class ScoreConverterImpl implements ScoreConverter {
	
	/**
	 * The maximum score which a Letter shall have.
	 */
	private static final double MAX_SCORE = 5.0;
	private final WeightedAlphabet weightedAlphabet;
	private final ScoreAdapter adapter;
	private final BinaryOperator<Double> mapper = (value, max) -> (MAX_SCORE * value) / max;
	
	/**
	 * Construct a new ScoreConverter using a starting WeightedAlphabet and a ScoreAdapter 
	 * which adapt the object WeightedAlphabet.
	 * 
	 * @param weightedAlphabet The WeightedAlphabet which have to be converted.
	 */
	public ScoreConverterImpl(final WeightedAlphabet weightedAlphabet) {
		this.weightedAlphabet = weightedAlphabet;
		this.adapter = new ScoreAdapter(this.weightedAlphabet, this.mapper);
	}
		
	/**
	 * {@inheritDoc}
	 */
	public WeightedAlphabet convert() {
		return new WeightedAlphabetImpl(this.adapter.getWeightedAlphabet());
	}

}
