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

 package model.emission;

import java.util.LinkedList;

import javax.swing.table.DefaultTableModel;

import org.uncommons.maths.number.NumberGenerator;

public interface IEmissionsequenceModel {

	/**
	 * Converts the sequence of emissions from a list into an array of doubles
	 * 
	 * @return array with the emissions as doubles
	 */
	double[] getEmissionsAsArray();

	/**
	 * Interferes the emissionsequence with a given confounder
	 * 
	 * @param confounder
	 *            the confounder which should be used for the interference
	 * @return the interfered emissionsequence
	 */
	EmissionsequenceModel interfereWith(NumberGenerator<?> confounder);

	/**
	 * Creates a DefaultTableModel from the absolute markovchain. This function
	 * is only called when the GUI creates a new matrix and it's needed that the
	 * markovchains are generated for the first time.
	 * 
	 * @return the DefaulttableModel with the absolute frequencies
	 */
	DefaultTableModel createMatrix();

	/**
	 * Creates a DefaultTableModel from the absolute markovchain.
	 * 
	 * @return the DefaulttableModel with the absolute frequencies
	 */
	DefaultTableModel getAbsMatrix();

	/**
	 * Creates a DefaultTableModel from the relative markovchain.
	 * 
	 * @return the DefaulttableModel with the relative frequencies
	 */
	DefaultTableModel getRelMatrix();

	LinkedList<Integer> getSequence();

	@Override
	String toString();
}
