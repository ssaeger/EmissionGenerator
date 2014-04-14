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

import java.security.GeneralSecurityException;

import org.uncommons.maths.random.AESCounterRNG;
import org.uncommons.maths.random.ExponentialGenerator;

/**
 * This class represents a model which generates a particular movementsequence.
 * 
 * @author Sebastian
 * 
 */
public class MoveModel extends Movementmodel {

	public MoveModel(String name, int id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public Movementsequence generateMovementsequence(int size) {
		ExponentialGenerator expGen;
		Movementsequence moveSeq = new Movementsequence();
		AESCounterRNG rnd;
		Move aktMove;

		try {
			rnd = new AESCounterRNG();
			expGen = new ExponentialGenerator(1, rnd);
			int steps;

			for (int i = 0; i <= size;) {
				// generate number of steps with the same move
				steps = (int) Math.round(expGen.nextValue());

				// generate current move
				aktMove = new Move(rnd.nextDouble(), rnd.nextDouble());

				// add current move
				for (int j = 0; j < steps && i < size; j++) {
					moveSeq.addMove(aktMove);
					i++;
				}
				// before generation of a new move slow down the old move
				// +1 to avoid a move with (0,0)
				moveSeq.addMove(aktMove.divideBy(rnd.nextInt(3) + 1));
				i++;
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
