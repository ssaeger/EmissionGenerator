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
