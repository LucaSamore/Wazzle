package wazzle.model.common;

import java.util.function.UnaryOperator;

public abstract class AbstractBonus {

	protected int quantity;
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void updateQuantity(final UnaryOperator<Integer> operation) {
		this.quantity = operation.apply(this.quantity);
	}
	
	public boolean isOver() {
		return this.quantity == 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quantity;
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
		AbstractBonus other = (AbstractBonus) obj;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractBonus [quantity=" + quantity + "]";
	}

}
