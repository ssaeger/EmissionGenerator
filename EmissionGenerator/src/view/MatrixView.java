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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import presenter.IMatrixPresenter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MatrixView extends JFrame implements IMatrixView {

	/**
	 * The presenter to communicate with.
	 */
	IMatrixPresenter matrixPresenter;

	private final JPanel contentPane;
	private final JTable table;
	private final JToggleButton tglbtnRelative;

	/**
	 * Create the frame.
	 */
	public MatrixView() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 700, 447);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(185dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JScrollPane scrollPane = new JScrollPane();
		this.contentPane.add(scrollPane, "2, 2, 7, 1, fill, fill");

		this.table = new JTable();
		scrollPane.setViewportView(this.table);

		this.tglbtnRelative = new JToggleButton("Relative frequencies");
		this.tglbtnRelative.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MatrixView.this.changeAppearance();
			}
		});
		this.contentPane.add(this.tglbtnRelative, "6, 4");
		this.setVisible(true);
	}

	private void changeAppearance() {
		this.matrixPresenter.changeAppearence(this.tglbtnRelative.isSelected());
	}

	@Override
	public void setTableModel(TableModel tableModel) {
		this.table.setModel(tableModel);
	}

	@Override
	public void setPresenter(IMatrixPresenter matrixPresenter) {
		this.matrixPresenter = matrixPresenter;
	}

	@Override
	public IMatrixPresenter getMatrixPresenter() {
		return this.matrixPresenter;
	}

}
