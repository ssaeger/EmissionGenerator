package ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class GUI {

	private JFrame frmEmissiongenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
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
		this.frmEmissiongenerator.setBounds(100, 100, 450, 300);
		this.frmEmissiongenerator
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmEmissiongenerator.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(90dlu;default):grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(61dlu;default):grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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

		JComboBox comboBox = new JComboBox();
		this.frmEmissiongenerator.getContentPane().add(comboBox,
				"4, 2, fill, default");

		JLabel lblNewLabel = new JLabel("Confounder:");
		this.frmEmissiongenerator.getContentPane().add(lblNewLabel,
				"6, 2, right, default");

		JComboBox comboBox_1 = new JComboBox();
		this.frmEmissiongenerator.getContentPane().add(comboBox_1,
				"8, 2, fill, default");

		JLabel lblSizeOfSequence = new JLabel("size of sequence:");
		this.frmEmissiongenerator.getContentPane().add(lblSizeOfSequence,
				"2, 4");

		JSpinner spinner = new JSpinner();
		this.frmEmissiongenerator.getContentPane().add(spinner, "4, 4");

		JButton btnGenerate = new JButton("Generate!");
		this.frmEmissiongenerator.getContentPane().add(btnGenerate, "8, 4");

		JTextPane textPane = new JTextPane();
		this.frmEmissiongenerator.getContentPane().add(textPane,
				"2, 6, 9, 1, fill, fill");

		JButton btnNewButton = new JButton("save in file...");
		this.frmEmissiongenerator.getContentPane().add(btnNewButton, "8, 8");
	}

}
