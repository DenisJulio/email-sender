package swing.app.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import swing.app.controller.WriteMessagePanelController;

public class WriteMessagePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private boolean isToAddressValid = false;
    private boolean isSubjectValid = false;
    private boolean isMessageContentValid = false;
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
        this.sendButtonGBC = createSendButtonGBC();

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        add(toInputField, toInputFieldGBC);
        add(subjectInputField, subjectInputFieldGBC);
        add(messageContentInputField, messageContentGBC);
        add(sendButton, sendButtonGBC);
    }

    private void updateSendButtonEnablingStatus() {
        var areAllInputsValid = (isToAddressValid && isSubjectValid) && isMessageContentValid;
        sendButton.setEnabled(areAllInputsValid);
    }

    // -------------------------------[Components]-----------------------------------------

    private InputField createToInputField() {
        return InputField.builder()
                .labelText("To:")
                .warnLabelText("<html>&#9888 e-mail in the form: <i>example@mail.com</i></html>")
                .validateInputBy(controller::isValidToInputField)
                .addInputValidityListener(evt -> {
                    isToAddressValid = (boolean) evt.getNewValue();
                    updateSendButtonEnablingStatus();
                })
                .build();
    }

    private InputField createSubjectInputField() {
        return InputField.builder()
                .labelText("Subject:")
                .warnLabelText("<html>&#9888 subject can't be empty</html>")
                .validateInputBy(controller::isValidSubjectInputField)
                .addInputValidityListener(evt -> {
                    isSubjectValid = (boolean) evt.getNewValue();
                    updateSendButtonEnablingStatus();
                })
                .build();
    }

    private InputField createMessageContentInputField() {
        return InputField.builder()
                .labelText("Content:")
                .warnLabelText("<html>&#9888 message content can't be empty</html>")
                .validateInputBy(controller::isValidContentMessage)
                .addInputValidityListener(evt -> {
                    isMessageContentValid = (boolean) evt.getNewValue();
                    updateSendButtonEnablingStatus();
                })
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
