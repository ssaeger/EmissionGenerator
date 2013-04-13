package presenter;

import java.awt.event.ActionEvent;

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
}
