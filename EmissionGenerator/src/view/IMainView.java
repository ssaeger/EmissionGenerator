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

package view;

import presenter.IMainPresenter;

public interface IMainView {

	/**
	 * Displays the given string in the readoutpanel.
	 * 
	 * @param s
	 *            the string to display
	 */
	void displayStatus(String s);

	void setPresenter(IMainPresenter mainPresenter);

	IMainPresenter getMainPresenter();

	int getLengthOfEmissionsequence();

	int getMovementmodelID();

	int getConfounderlID();

	boolean getBoxStatus();
}
