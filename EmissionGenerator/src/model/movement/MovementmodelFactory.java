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
 * This class is a factory which creates the selected model. It's implemented as
 * a sigleton.
 * 
 * @author Sebastian
 * 
 */
public class MovementmodelFactory {
	/**
	 * Array with the names of all models
	 */
	public static final String[] MODELLIST = { "MoveModel", "EatModel" };

	private static MovementmodelFactory instance;

	private MovementmodelFactory() {
	}

	public static MovementmodelFactory getInstance() {
		if (instance == null) {
			instance = new MovementmodelFactory();
		}
		return instance;
	}

	/**
	 * Creates the selected model.
	 * 
	 * @param id
	 *            the id of the model
	 * @return the created model
	 */
	public Movementmodel createModel(int id) {
		String modelName = MODELLIST[id];
		switch (modelName) {
		case "MoveModel":
			return new MoveModel(MODELLIST[id], id);
		case "EatModel":
			return new EatModel(MODELLIST[id], id);
		default:
			throw new IllegalArgumentException("Wrong model number!");
		}
	}
}