package presenter;

import model.emission.IEmissionsequenceModel;
import view.IMatrixView;

public class MatrixPresenter implements IMatrixPresenter {

	private IMatrixView matrixView;
	private IEmissionsequenceModel emissionsequenceModel;

	@Override
	public void changeAppearence(boolean rel) {
		if (rel) {
			this.matrixView.setTableModel(this.emissionsequenceModel
					.getRelMatrix());
		} else {
			this.matrixView.setTableModel(this.emissionsequenceModel
					.getAbsMatrix());
		}
	}

	@Override
	public void createMatrix() {
		this.matrixView
				.setTableModel(this.emissionsequenceModel.createMatrix());
	}

	@Override
	public void setView(IMatrixView view) {
		this.matrixView = view;
	}

	@Override
	public void setModel(IEmissionsequenceModel model) {
		this.emissionsequenceModel = model;
	}

}
