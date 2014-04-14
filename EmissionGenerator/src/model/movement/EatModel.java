 /**
  *	 Copyright 2013, 2014 Sebastian Säger
  */

 /**   
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package model.movement;

import java.security.GeneralSecurityException;

import org.uncommons.maths.random.AESCounterRNG;

/**
 * This class represents a model which generates a particular movementsequence.
 * 
 * @author Sebastian
 * 
 */
public class EatModel extends Movementmodel {

	public EatModel(String name, int id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public Movementsequence generateMovementsequence(int size) {
		Movementsequence moveSeq = new Movementsequence();
		AESCounterRNG rnd;

		try {
			rnd = new AESCounterRNG();
			for (int i = 0; i <= size; i++) {

				// generate and add current move
				moveSeq.addMove(new Move(rnd.nextDouble(), rnd.nextDouble()));

			}
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		return moveSeq;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
