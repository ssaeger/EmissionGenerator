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
