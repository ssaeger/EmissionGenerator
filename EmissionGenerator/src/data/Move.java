package data;

public class Move {

	double x;
	double y;

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

}
