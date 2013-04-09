package data;

/**
 * This class represents a single move.
 * 
 * @author Sebastian
 * 
 */
public class Move {

	/**
	 * the x- and y-coordinate of the movementvector
	 */
	private final double x;
	private final double y;

	/**
	 * Creates a new move with the given x and y values.
	 * 
	 * @param x
	 *            x-value of the movementvector
	 * @param y
	 *            y-value of the movementvector
	 */
	public Move(double x, double y) {
		this.x = Math.rint(x * 100) / 100;
		this.y = Math.rint(y * 100) / 100;
	}

	/**
	 * Multiplies this Move with the given multiplier.
	 * 
	 * @param multiplier
	 *            value to multiply with
	 * @return the multiplied Move
	 */
	public Move multiplyWith(double multiplier) {
		return new Move(this.x * multiplier, this.y * multiplier);
	}

	/**
	 * Divides this Move by the given divisior.
	 * 
	 * @param divisor
	 *            value to divide by
	 * @return the divided Move
	 */
	public Move divideBy(int divisor) {
		return new Move(this.x / divisor, this.y / divisor);
	}

	/**
	 * Calculates the difference of the lengths of the given move
	 * 
	 * @param m2
	 *            the second move
	 * @return
	 */
	public double lengthDifference(Move m2) {
		return Math.sqrt(m2.getX() * m2.getX() + m2.getY() * m2.getY())
				- Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
}
