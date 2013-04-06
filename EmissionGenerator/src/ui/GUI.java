package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.uncommons.maths.number.NumberGenerator;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import data.ConfounderFactory;
import data.Emissionsequence;
import data.Movementsequence;
import data.model.Model;
import data.model.ModelFactory;

public class GUI {

	private JFrame frmEmissiongenerator;
	private JSpinner spinnerSize;
	private JComboBox comboModel;
	private JComboBox comboConfounder;
	private JTextArea textReadout;
	private JCheckBox chckbxShow;
	private ModelFactory modelFactory;
	private ConfounderFactory confounderFactory;
	private Model model;
	private NumberGenerator<?> confounder;
	private Emissionsequence emisSeq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					GUI window = new GUI();
					window.frmEmissiongenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmEmissiongenerator = new JFrame();
		this.frmEmissiongenerator.setTitle("EmissionGenerator");
		this.frmEmissiongenerator.setBounds(100, 100, 600, 300);
		this.frmEmissiongenerator
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmEmissiongenerator.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(48dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(66dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(40dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(65dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(49dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, }));

		JLabel lblModel = new JLabel("Model:");
		this.frmEmissiongenerator.getContentPane().add(lblModel,
				"2, 2, right, default");

		this.comboModel = new JComboBox(ModelFactory.MODELLIST);
		this.frmEmissiongenerator.getContentPane().add(this.comboModel,
				"4, 2, fill, default");

		JLabel lblConfounder = new JLabel("Confounder:");
		this.frmEmissiongenerator.getContentPane().add(lblConfounder,
				"6, 2, right, default");

		this.comboConfounder = new JComboBox(ConfounderFactory.CONFOUNDERLIST);
		this.frmEmissiongenerator.getContentPane().add(this.comboConfounder,
				"8, 2, 5, 1, fill, default");

		JLabel lblSizeOfSequence = new JLabel("size of sequence:");
		this.frmEmissiongenerator.getContentPane().add(lblSizeOfSequence,
				"2, 4");

		this.spinnerSize = new JSpinner();
		this.spinnerSize.setModel(new SpinnerNumberModel(new Integer(10),
				new Integer(1), null, new Integer(1)));
		this.frmEmissiongenerator.getContentPane()
				.add(this.spinnerSize, "4, 4");

		JButton btnGenerate = new JButton("Generate!");
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GUI.this.generateSequence();
			}
		});
		this.frmEmissiongenerator.getContentPane().add(btnGenerate, "8, 4");

		JLabel lblEmissionsequence = new JLabel("Emissionsequence:");
		this.frmEmissiongenerator.getContentPane().add(lblEmissionsequence,
				"2, 6");

		this.chckbxShow = new JCheckBox(
				"Show Emissionsequence in following box");
		this.chckbxShow
				.setToolTipText("For better performance the emissionsequence will not shown automatically");
		this.frmEmissiongenerator.getContentPane().add(this.chckbxShow,
				"8, 6, 5, 1");

		JScrollPane scrollPane = new JScrollPane();
		this.frmEmissiongenerator.getContentPane().add(scrollPane,
				"2, 8, 11, 1, fill, fill");

		this.textReadout = new JTextArea();
		scrollPane.setViewportView(this.textReadout);
		this.textReadout.setEditable(false);
		this.textReadout.setLineWrap(true);
		this.textReadout.setWrapStyleWord(true);

		JButton btnHistogram = new JButton("Histogram");
		btnHistogram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HistogramDataset histData = new HistogramDataset();
				histData.setType(HistogramType.FREQUENCY);
				double[] values = GUI.this.emisSeq.getEmissionsAsArray();
				histData.addSeries("H1", values, Emissionsequence.EMISSIONCOUNT);

				JFreeChart chart = ChartFactory.createHistogram("Histogramm",
						"EmissionID", "Frequency", histData,
						PlotOrientation.VERTICAL, false, false, false);
				new Histogram(new ChartPanel(chart));
			}
		});

		JButton btnInterfere = new JButton("Interfere");
		btnInterfere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GUI.this.confounder = GUI.this.confounderFactory
						.createConfounder(GUI.this.comboConfounder
								.getSelectedIndex());
				GUI.this.emisSeq = GUI.this.emisSeq
						.interfereWith(GUI.this.confounder);
				GUI.this.updateReadout("Emissionsequence interfered!");
			}
		});
		btnInterfere
				.setToolTipText("Interfere the Emissionsequence with the selected confounder");
		this.frmEmissiongenerator.getContentPane().add(btnInterfere, "4, 10");
		this.frmEmissiongenerator.getContentPane().add(btnHistogram, "6, 10");

		JButton btnMatrix = new JButton("Stochastic Matrix");
		btnMatrix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Matrix(GUI.this.emisSeq);
			}
		});
		this.frmEmissiongenerator.getContentPane().add(btnMatrix, "8, 10");

		JButton btnSave = new JButton("save to file...");
		this.frmEmissiongenerator.getContentPane().add(btnSave, "10, 10");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();

				if (fc.showSaveDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(file);
						fw.write(GUI.this.emisSeq.toString());
						fw.flush();
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		this.modelFactory = ModelFactory.getInstance();
		this.confounderFactory = ConfounderFactory.getInstance();
	}

	private void generateSequence() {
		int size = Integer.valueOf(this.spinnerSize.getValue().toString())
				.intValue();
		this.model = this.modelFactory.createModel(this.comboModel
				.getSelectedIndex());
		Movementsequence movSeq = this.model.generateMovementsequence(size);
		this.emisSeq = new Emissionsequence(movSeq);
		this.updateReadout("Emissionsequence was generated successfully!");
	}

	private void updateReadout(String s) {
		if (this.chckbxShow.isSelected()) {
			this.textReadout.setText(this.emisSeq.toString());
		} else {
			this.textReadout.setText(s);
		}
	}
}
