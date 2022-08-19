package swing.app.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import swing.app.controller.WriteMessagePanelController;

public class WriteMessagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private WriteMessagePanelController controller;
	private InputField toInputField;
	private InputField subjectInputField;
	private JLabel contentLabel;
	private JTextArea messageContentTextArea;
	private JScrollPane scrollableTextAreaContainer;
	private JButton sendButton;

	private GridBagConstraints toInputFieldGBC;
	private GridBagConstraints subjectInputFieldGBC;
	private GridBagConstraints contentLabelGBC;
	private GridBagConstraints scrollableTextAreaContainerGBC;
	private GridBagConstraints sendButtonGBC;

	public WriteMessagePanel(WriteMessagePanelController controller) {
		this.controller = controller;
		this.toInputField = createToInputField();
		this.subjectInputField = createSubjectInputField();
		contentLabel = createContentLabel();
		messageContentTextArea = createMessageContentTextArea();
		scrollableTextAreaContainer = createScrollableTextAreaContainer(messageContentTextArea);
		sendButton = createSendButton();

		this.toInputFieldGBC = createToInputFieldGBC();
		this.subjectInputFieldGBC = createSubjectInputFieldGBC();
		contentLabelGBC = createContentLabelGBC();
		scrollableTextAreaContainerGBC = createScrollableTextAreaContainerGBC();
		sendButtonGBC = createSendButtonGBC();

		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		
		add(toInputField, toInputFieldGBC);
		add(subjectInputField, subjectInputFieldGBC);
		add(contentLabel, contentLabelGBC);
		add(scrollableTextAreaContainer, scrollableTextAreaContainerGBC);
		add(sendButton, sendButtonGBC);
	}

	// -------------------------------[Components]-----------------------------------------

	private InputField createToInputField() {
		return InputField.builder()
				.labelText("To:")
				.warnLabelText("<html>⚠ e-mail in the form: <i>example@mail.com</i></html>")
				.validateInputBy(controller::isValidToInputField)
				.build();
	}

	private InputField createSubjectInputField() {
		return InputField.builder()
				.labelText("Subject:")
				.warnLabelText("⚠ subject can't be empty")
				.validateInputBy(controller::isValidSubjectInputField)
				.build();
	}
	
	private JLabel createContentLabel() {
		return new JLabel("Content:");
	}

	private JTextArea createMessageContentTextArea() {
		var textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setLineWrap(true);
		textArea.setAutoscrolls(true);
		textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return textArea;
	}

	private JScrollPane createScrollableTextAreaContainer(JTextArea textArea) {
		var scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 300));
		scrollPane.setMinimumSize(new Dimension(400, 300));
		return scrollPane;
	}

	private JButton createSendButton() {
		var button = new JButton("Send");
		button.setEnabled(false);
		return button;
	}

	// -------------------------------[GridBagConstraints]-----------------------------------

	private GridBagConstraints createToInputFieldGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0, 0, 5, 0);
		return constraints;
	}

	private GridBagConstraints createSubjectInputFieldGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0, 0, 5, 0);
		return constraints;
	}

	private GridBagConstraints createContentLabelGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0, 0, 5, 0);
		return constraints;
	}

	private GridBagConstraints createScrollableTextAreaContainerGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0, 0, 5, 0);
		return constraints;
	}
	
	private GridBagConstraints createSendButtonGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		constraints.ipady = 8;
		return constraints;
	}
}
