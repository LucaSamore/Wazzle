package wazzle.model.common;

import java.util.function.UnaryOperator;

public interface BonusManager {
	
	void updateScoreBonusQuantity(UnaryOperator<Integer> operation);
	
	void updateWordBonusQuantity(UnaryOperator<Integer> operation);
	
	void updateTimeBonusQuantity(UnaryOperator<Integer> operation);

}
