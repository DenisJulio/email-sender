package swing.app.ui;

import static java.util.Objects.nonNull;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import lombok.Getter;

/**
 * Defines a component that receives user input and validates its correctness.
 * It displays a {@code JLabel} as a label describing the input to be entered,
 * and a warning label that is made visible as the input provided by the user
 * remains invalid.
 */
public final class InputField extends JPanel implements DocumentListener, CaretListener {

	private static final long serialVersionUID = 1L;
	public static final int TEXT_FIELD = 0;
	public static final int TEXT_AREA = 1;

	private JLabel mainLabel;
	private JLabel warnLabel;
	@Getter
	private JTextComponent inputTextComponent;
	private Box labelsBox;
	private GridBagConstraints labelsBoxGBC;
	private GridBagConstraints inputTextComponentGBC;
	@Getter
	/**
	 * Tracks the validity state of the user provided input.
	 */
	private boolean validInput;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final transient Predicate<String> validationLogic;
	
	private InputField(String mainLabelText, String warnLabelText, Predicate<String> validationLogic,
			int textComponentType) {
		this.validationLogic = validationLogic;
		this.mainLabel = createMainLabel(mainLabelText);
		this.warnLabel = createWarnLabel(warnLabelText);
		this.inputTextComponent = createInputTextComponent(textComponentType);
		this.labelsBox = createLabelsBox(mainLabel, warnLabel);
		this.labelsBoxGBC = createLabelsBoxGBC();
		this.inputTextComponentGBC = createInputTextComponentGBC();
		// ----------- Layout placement
		setLayout(createLayout());
		add(labelsBox, labelsBoxGBC);
		if (inputTextComponent instanceof JTextArea textArea) {
			var scroollContainer = createScrollableTextAreaContainer(textArea);
			add(scroollContainer, inputTextComponentGBC);
		} else {
			add(inputTextComponent, inputTextComponentGBC);
		}
	}

	/**
	 * Attempts to retrieve the validated user provided input.
	 * @return An {@code Optional<String>} with the validated user input,
	 * or an {@code Optional.empty()} if the provided input is invalid.
	 */
	public Optional<String> getValidatedInput() {
		var isInputValid = this.validationLogic.test(this.inputTextComponent.getText());
		return isInputValid 
				? Optional.of(this.inputTextComponent.getText()) 
				: Optional.empty();
	}
	
	private void setValidInput(boolean newValue) {
		var oldValue = this.validInput;
		this.validInput = newValue;
		pcs.firePropertyChange("validInput", oldValue, newValue);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	// -------------------------[Listeners]-----------------------------------
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		if (nonNull(this.validationLogic)) {
			var doc = e.getDocument();
			String text = "";
			try {
				text = doc.getText(0, doc.getEndPosition().getOffset());
			} catch (BadLocationException exception) {
				exception.printStackTrace();
			}
			setValidInput(this.validationLogic.test(text));
			warnLabel.setVisible(!isValidInput());
		}
	}
	
	@Override
	public void removeUpdate(DocumentEvent e) {
		insertUpdate(e);
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		 insertUpdate(e);
	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		if (nonNull(this.validationLogic)) {
			var textComponent = (JTextComponent) e.getSource();
			setValidInput(this.validationLogic.test(textComponent.getText()));
			warnLabel.setVisible(!isValidInput());
		}
	}
	
	// ------------------------[Component Initialization]------------------------------

	private JLabel createMainLabel(String mainLabelText) {
		return new JLabel(mainLabelText);
	}

	private JLabel createWarnLabel(String warnLabelText) {
		var font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
		var label = new JLabel();
		label.setFont(font);
		label.setText(warnLabelText);
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setForeground(Color.red);
		label.setVisible(false);
		return label;
	}

	private Box createLabelsBox(JLabel label, JLabel wLabel) {
		var box = Box.createHorizontalBox();
		box.add(label);
		box.add(Box.createHorizontalGlue());
		box.add(wLabel);
		return box;
	}

	private JTextField createInputField() {
		var textField = new JTextField();
		textField.getDocument().addDocumentListener(this);
		textField.addCaretListener(this);
		return textField;
	}

	private JTextArea createTextAreaComponent() {
		var textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setLineWrap(true);
		textArea.setAutoscrolls(true);
		textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		textArea.getDocument().addDocumentListener(this);
		textArea.addCaretListener(this);
		return textArea;
	}
	
	private JScrollPane createScrollableTextAreaContainer(JTextArea textArea) {
		var scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 300));
		scrollPane.setMinimumSize(new Dimension(400, 300));
		return scrollPane;
	}
	
	private JTextComponent createInputTextComponent(int textComponentType) {
		return switch (textComponentType) {
			case TEXT_FIELD -> createInputField();
			case TEXT_AREA -> createTextAreaComponent();
			default ->
				throw new IllegalArgumentException("Unexpected value: " + textComponentType);
		};
	}
	
	// --------------------------------[Layout Manager]-------------------------------
	
	private GridBagLayout createLayout() {
		var layout = new GridBagLayout();
		layout.columnWeights = new double[] { 1.0 };
		return layout;
	}
	
	// ---------------------------[GridBagConstraints]--------------------------------

	private GridBagConstraints createLabelsBoxGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0, 0, 5, 0);
		return constraints;
	}
	
	private GridBagConstraints createInputTextComponentGBC() {
		var constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.ipady = 5;
		return constraints;
	}

	// ----------------------------[Builder]---------------------------------------------

	public static class Builder {
		private String labelText;
		private String warnLabelText;
		private Predicate<String> validationLogic;
		private PropertyChangeListener listener;
		private int textComponentType = 0;
		
		public Builder labelText(String text) {
			this.labelText = text;
			return this;
		}

		public Builder warnLabelText(String text) {
			this.warnLabelText = text;
			return this;
		}

		/**
		 *Logic used internally to determine weather user input is valid or not. 
		 * @param validationLogic
		 */
		public Builder validateInputBy(Predicate<String> validationLogic) {
			this.validationLogic = validationLogic;
			return this;
		}
		
		/**
		 *A {@code PropertyChangeListener} that listens for changes in the validity
		 *state of the provided user input.
		 *Calls to {@code event.getNewValue()} and {@code event.getOldValue()} returns:
		 *<ul>
		 *	<li><b>true</b> if valid</li>
		 *	<li><b>false</b> if invalid</li>
		 *</ul>
		 * @param listener
		 */
		public Builder addInputValidityListener(PropertyChangeListener listener) {
			this.listener = listener;
			return this;
		}
		
		public Builder textComponentType(int textComponentType) {
			this.textComponentType = textComponentType;
			return this;
		}

		public InputField build() {
			var inputField = new InputField(labelText, warnLabelText, validationLogic, textComponentType);
			inputField.pcs.addPropertyChangeListener("validInput", listener);
			return inputField;
		}
	}
}
