package data;

public class Move {

	private final double x;
	private final double y;

	public Move(double x, double y) {
		this.x = Math.rint(x * 100) / 100;
		this.y = Math.rint(y * 100) / 100;
	}

	public Move multiplyWith(double multiplier) {
		return new Move(this.x * multiplier, this.y * multiplier);
	}

	public Move divideBy(int divisor) {
		return new Move(this.x / divisor, this.y / divisor);
	}

	public Move subtract(Move subtrahend) {
		return new Move(this.x - subtrahend.getX(), this.y - subtrahend.getY());
	}

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
