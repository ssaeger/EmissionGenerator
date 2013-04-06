package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import data.Emissionsequence;

public class Matrix extends JFrame {

	private final JPanel contentPane;
	private final JTable table;
	private final JToggleButton tglbtnRelative;
	private final Emissionsequence emisSeq;

	/**
	 * Create the frame.
	 */
	public Matrix(Emissionsequence sequence) {
		this.emisSeq = sequence;
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
		// this.table.setModel(new DefaultTableModel(new Object[][] {
		// { "0", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "1", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "2", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "3", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "4", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "5", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "6", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "7", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "8", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "9", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "10", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "11", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "12", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "13", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "14", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "15", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "16", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "17", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "18", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "19", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null },
		// { "20", null, null, null, null, null, null, null, null, null,
		// null, null, null, null, null, null, null, null, null,
		// null, null, null }, }, new String[] { "Emission", "0",
		// "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
		// "13", "14", "15", "16", "17", "18", "19", "20" }));
		this.table.setModel(this.emisSeq.createMatrix());
		scrollPane.setViewportView(this.table);

		this.tglbtnRelative = new JToggleButton("Relative frequencies");
		this.tglbtnRelative.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Matrix.this.tglbtnRelative.isSelected()) {
					Matrix.this.table.setModel(Matrix.this.emisSeq
							.getRelMatrix());
				} else {
					Matrix.this.table.setModel(Matrix.this.emisSeq
							.getAbsMatrix());
				}
			}
		});
		this.contentPane.add(this.tglbtnRelative, "6, 4");
		this.setVisible(true);
	}

}
