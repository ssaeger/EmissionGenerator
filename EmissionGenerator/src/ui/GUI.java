package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import org.uncommons.maths.number.NumberGenerator;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import data.ConfounderFactory;
import data.Movementsequence;
import data.model.Model;
import data.model.ModelFactory;

public class GUI {

	private JFrame frmEmissiongenerator;
	private JSpinner spinnerSize;
	private JComboBox comboModel;
	private JComboBox comboConfounder;
	private ModelFactory modelFactory;
	private ConfounderFactory confounderFactory;
	private Model model;
	private NumberGenerator<?> confounder;

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
						ColumnSpec.decode("max(61dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
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
				"8, 2, 3, 1, fill, default");

		JLabel lblSizeOfSequence = new JLabel("size of sequence:");
		this.frmEmissiongenerator.getContentPane().add(lblSizeOfSequence,
				"2, 4");

		this.spinnerSize = new JSpinner();
		this.spinnerSize.setModel(new SpinnerNumberModel(new Integer(1),
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

		JTextPane textReadout = new JTextPane();
		textReadout.setEditable(false);
		this.frmEmissiongenerator.getContentPane().add(textReadout,
				"2, 6, 9, 1, fill, fill");

		JButton btnSave = new JButton("save in file...");
		this.frmEmissiongenerator.getContentPane().add(btnSave, "8, 8");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();

				if (fc.showSaveDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
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
		this.confounder = this.confounderFactory
				.createConfounder(this.comboConfounder.getSelectedIndex());
		Movementsequence mov = this.model.generateMovementsequence(size);
		int i = 6;

	}
}
