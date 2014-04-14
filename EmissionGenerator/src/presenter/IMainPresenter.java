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

package presenter;

import java.awt.event.ActionEvent;

import view.IMainView;

public interface IMainPresenter {

	/**
	 * Initiates the prozess to generate an emissionsequence.
	 */
	void generateSequences();

	/**
	 * Interferes the emissionsequence with the selected confounder.
	 */
	void interfereSequence();

	/**
	 * Displays the emissionsequence or status in the readoutpanel.
	 * 
	 * @param s
	 *            the string to display
	 */
	void displayStatus(String s);

	/**
	 * Saves the emissionsequence to a file.
	 * 
	 * @param e
	 *            the ActionEvent
	 */
	void saveEmissionsequenceToFile(ActionEvent e);

	/**
	 * Loads the emissionsequence from a file.
	 * 
	 * @param e
	 *            the ActionEvent
	 */
	void loadEmissionsequenceFromFile(ActionEvent e);

	/**
	 * Loads the movementsequence from a file.
	 * 
	 * @param e
	 *            the ActionEvent
	 */
	void loadMovementsequenceFromFile(ActionEvent e);

	/**
	 * Saves the movementsequence to a file.
	 * 
	 * @param e
	 *            the ActionEvent
	 */
	void saveMovementsequenceToFile(ActionEvent e);

	/**
	 * Creates a histogram of the emissionsequence.
	 */
	void createHistogram();

	/**
	 * Creates a stochastic matrix of the emissionsequence.
	 */
	void createStochasticMatrix();

	/**
	 * Sets the view.
	 */
	void setView(IMainView view);
}
