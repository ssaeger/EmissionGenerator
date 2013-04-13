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
