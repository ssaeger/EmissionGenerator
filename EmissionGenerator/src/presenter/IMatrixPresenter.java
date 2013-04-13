package presenter;

import model.emission.IEmissionsequenceModel;
import view.IMatrixView;

public interface IMatrixPresenter {

	void changeAppearence(boolean rel);

	/**
	 * Sets the view.
	 */
	void setView(IMatrixView view);

	void setModel(IEmissionsequenceModel model);

	void createMatrix();
}
