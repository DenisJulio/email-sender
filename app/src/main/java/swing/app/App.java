package swing.app;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class App {

	private JFrame frame;

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
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		var contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(0, 1));
		frame.setContentPane(contentPane);
		
		var textArea = new JTextArea();
		textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		textArea.setAutoscrolls(true);
		textArea.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		contentPane.add(textArea);
	}
}
