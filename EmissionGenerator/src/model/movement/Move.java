 /**
  *	 Copyright 2013, 2014 Sebastian Säger
  */

 /**   
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published 
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package model.movement;

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

	private final double angle;
	private final double length;

	/**
	 * Creates a new move with the given x and y values.
	 * 
	 * @param x
	 *            x-value of the movementvector
	 * @param y
	 *            y-value of the movementvector
	 */
	public Move(double x, double y) {
		// only two decimal places
		this.x = Math.rint(x * 100) / 100;
		this.y = Math.rint(y * 100) / 100;
		this.length = Math
				.rint(Math.sqrt(this.x * this.x + this.y * this.y) * 100) / 100;
		this.angle = Math.toDegrees(Math.atan2(this.y, this.x));
	}

	/**
	 * Creates a new move with the given angle and length.
	 * 
	 * @param angle
	 *            angle of the movementvector
	 * @param length
	 *            length of the movementvector
	 * @param polarcoordinate
	 *            doesn't matter if true or false, does nothing, only because
	 *            the signaturs of the two constructores are otherwiese the
	 *            same.
	 */
	public Move(double angle, double length, boolean polarcoordinate) {
		// only two decimal places
		this.x = Math.rint(length * Math.cos(Math.toRadians(angle)) * 100) / 100;
		this.y = Math.rint(length * Math.sin(Math.toRadians(angle)) * 100) / 100;
		this.length = Math.rint(length * 100) / 100;
		this.angle = Math.rint(angle * 100) / 100;
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

	@Override
	public String toString() {
		return "(" + this.x + "|" + this.y + ")";
	}
}
