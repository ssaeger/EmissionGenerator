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

package model;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.GaussianGenerator;

/**
 * This class is a factory which creates the selected confounder. It's
 * implemented as a sigleton.
 * 
 * @author Sebastian
 * 
 */
public class ConfounderFactory {
	public static final String[] CONFOUNDERLIST = {
			"Normal distribution, \u03C3 = 1.0",
			"Normal distribution, \u03C3 = 2.0",
			"Normal distribution, \u03C3 = 5.0",
			"Normal distribution, \u03C3 = 10.0",
			"Normal distribution, \u03C3 = 15.0",
			"Normal distribution, \u03C3 = 20.0",
			"Normal distribution, \u03C3 = 50.0" };

	private static ConfounderFactory instance;

	private ConfounderFactory() {
	}

	public static ConfounderFactory getInstance() {
		if (instance == null) {
			instance = new ConfounderFactory();
		}
		return instance;
	}

	/**
	 * Creates the selected confounder.
	 * 
	 * @param id
	 *            id of the selected confounder
	 * @return the created confounder
	 */
	public NumberGenerator<?> createConfounder(int id) {
		String confounderName = CONFOUNDERLIST[id];
		switch (confounderName) {
		case "Normal distribution, \u03C3 = 1.0":
			return new GaussianGenerator(0, 1, new Random());
		case "Normal distribution, \u03C3 = 2.0":
			return new GaussianGenerator(0, 2.0, new Random());
		case "Normal distribution, \u03C3 = 5.0":
			return new GaussianGenerator(0, 5.0, new Random());
		case "Normal distribution, \u03C3 = 10.0":
			return new GaussianGenerator(0, 10.0, new Random());
		case "Normal distribution, \u03C3 = 15.0":
			return new GaussianGenerator(0, 15.0, new Random());
		case "Normal distribution, \u03C3 = 20.0":
			return new GaussianGenerator(0, 20.0, new Random());
		case "Normal distribution, \u03C3 = 50.0":
			return new GaussianGenerator(0, 50.0, new Random());
		default:
			throw new IllegalArgumentException("Wrong confounder number!");
		}
	}
}
