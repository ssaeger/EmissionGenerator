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
