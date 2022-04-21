package wazzle.model.common;

import java.util.function.UnaryOperator;

import com.google.gson.annotations.Expose;

/**
 * AbstractBonus offers an abstraction for all the Bonuses.
 *
 */
public abstract class AbstractBonus {

	@Expose
	protected int quantity;
	protected String name;
	
	/**
	 * Returns the quantity of the Bonus.
	 * 
	 * @return int which represent the quantity.
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Returns the name of the Bonus.
	 * 
	 * @return String which represent the quantity.
	 */
	public String getName() {
		return this.name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractBonus [quantity=" + quantity + ", name=" + name + "]";
	}

}
