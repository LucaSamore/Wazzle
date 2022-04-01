package wazzle.model.common;

import java.util.function.UnaryOperator;

/**
 * AbstractBonus offers an abstraction for all the Bonuses.
 *
 */
public abstract class AbstractBonus {

	protected int quantity;
	
	/**
	 * Returns the quantity of the Bonus.
	 * 
	 * @return int which represent the quantity.
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Update the quantity of the bonus.
	 * 
	 * @param operation represent the operation that needs to be done.
	 */
	public void updateQuantity(final UnaryOperator<Integer> operation) {
		this.quantity = operation.apply(this.quantity);
	}
	
	/**
	 * Check if the quantity is over or not.
	 * 
	 * @return boolean which returns if the Bonus is over or not.
	 */
	public boolean isOver() {
		return this.quantity == 0;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quantity;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "AbstractBonus [quantity=" + quantity + "]";
	}

}
