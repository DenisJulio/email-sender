package swing.app.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import swing.app.controller.WriteMessagePanelController;

public class WriteMessagePanel extends JPanel {
	
	private WriteMessagePanelController controller;
	private JTextField subjectTextField;
	private JTextArea	messageContentTextArea;
	private JScrollPane scrollableTextAreaContainer;
	private GridBagConstraints subjectTextFieldGBC; 
	private GridBagConstraints scrollableTextAreaContainerGBC;

	public WriteMessagePanel(WriteMessagePanelController controller) {
		this.controller = controller;
		subjectTextField = createSubjectTextField();
		messageContentTextArea = createMessageContentTextArea();
		scrollableTextAreaContainer = createScrollableTextAreaContainer(messageContentTextArea);
		subjectTextFieldGBC = createSubjectTextFieldGBC(); 
		scrollableTextAreaContainerGBC = createScrollableTextAreaContainerGBC();
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		add(subjectTextField, subjectTextFieldGBC);
		add(scrollableTextAreaContainer, scrollableTextAreaContainerGBC);
	}
	
	//-------------------------------[Components]-----------------------------------------

	private JTextField createSubjectTextField() {
		var textField = new JTextField();
		return textField;
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

	//-------------------------------[GridBagConstraints]-----------------------------------
	
	private GridBagConstraints createSubjectTextFieldGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0, 0, 5, 0);
		return constraints;
	}
	
	private GridBagConstraints createScrollableTextAreaContainerGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		return constraints;
	}
}
