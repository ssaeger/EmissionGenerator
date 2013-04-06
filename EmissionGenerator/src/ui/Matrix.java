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
