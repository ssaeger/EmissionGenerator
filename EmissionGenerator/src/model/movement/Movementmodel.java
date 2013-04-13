package model.movement;



public abstract class Movementmodel {

	String name;
	int id;

	/**
	 * Creates the movementsequence.
	 * 
	 * @param size
	 *            the size of the movementsequence.
	 * @return the generated movementsequence
	 */
	public abstract Movementsequence generateMovementsequence(int size);

	/**
	 * Gets the name of the current model.
	 * 
	 * @return the name of the current model
	 */
	public abstract String getName();
}
