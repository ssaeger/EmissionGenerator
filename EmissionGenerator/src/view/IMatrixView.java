package view;

import javax.swing.table.TableModel;

import presenter.IMatrixPresenter;

public interface IMatrixView {

	void setPresenter(IMatrixPresenter matrixPresenter);

	IMatrixPresenter getMatrixPresenter();

	void setTableModel(TableModel tableModel);
}
