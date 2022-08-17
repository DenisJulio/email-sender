package swing.app.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import jakarta.mail.PasswordAuthentication;
import swing.app.controller.WriteMessagePanelController;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;

public class App {

	private JFrame frame;
	private WriteMessagePanel writeMessagePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			App window = new App();
			window.frame.setVisible(true);
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public App() {
		var controller = new WriteMessagePanelController();
		writeMessagePanel = new WriteMessagePanel(controller);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(writeMessagePanel);
		frame.pack();
	}
}
