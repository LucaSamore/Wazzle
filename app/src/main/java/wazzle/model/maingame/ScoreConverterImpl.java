package wazzle.model.maingame;

import java.util.function.BinaryOperator;

public final class ScoreConverterImpl implements ScoreConverter {
	
	/**
	 * The maximum score which a Letter shall have.
	 */
	private static final double MAX_SCORE = 6.0;
	private static final double MIN_SCORE = 1.0;
	private static final double ERROR = 0.1;
	private final WeightedAlphabet weightedAlphabet;
	private final ScoreAdapter adapter;
	private final BinaryOperator<Double> mapper = (value, max) -> MAX_SCORE - MAX_SCORE * value / max + MIN_SCORE 
																									  + ERROR;
	
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
