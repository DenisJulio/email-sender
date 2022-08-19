package swing.app.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import swing.app.controller.WriteMessagePanelController;

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
