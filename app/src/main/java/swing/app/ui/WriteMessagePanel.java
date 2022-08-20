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
	private InputField messageContentInputField;
	private JButton sendButton;

	private GridBagConstraints toInputFieldGBC;
	private GridBagConstraints subjectInputFieldGBC;
	private GridBagConstraints messageContentGBC;
	private GridBagConstraints sendButtonGBC;

	public WriteMessagePanel(WriteMessagePanelController controller) {
		this.controller = controller;
		this.toInputField = createToInputField();
		this.subjectInputField = createSubjectInputField();
		this.messageContentInputField = createMessageContentInputField();
		this.sendButton = createSendButton();

		this.toInputFieldGBC = createToInputFieldGBC();
		this.subjectInputFieldGBC = createSubjectInputFieldGBC();
		this.messageContentGBC = createMessageContentGBC();
		sendButtonGBC = createSendButtonGBC();

		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		
		add(toInputField, toInputFieldGBC);
		add(subjectInputField, subjectInputFieldGBC);
		add(messageContentInputField, messageContentGBC);
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
	
	private InputField createMessageContentInputField() {
		return InputField.builder()
				.labelText("Content:")
				.warnLabelText("⚠ message content can't be empty")
				.validateInputBy(controller::isValidContentMessage)
				.textComponentType(InputField.TEXT_AREA)
				.build();
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

	private GridBagConstraints createMessageContentGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0, 0, 5, 0);
		return constraints;
	}
	
	private GridBagConstraints createSendButtonGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		constraints.ipady = 8;
		return constraints;
	}
}
