package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class LoginController {

	@FXML
	private Label btnForgot;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnPeek;

	@FXML
	private Label btnSignUp;

	@FXML
	private ImageView imgPeek;

	@FXML
	private Label lblWarning;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtUsername;

	@FXML
	void btnPressed(ActionEvent event) {
		if (event.getSource() == btnLogin) {
			validateLoginInfor();
		} else if (event.getSource() == btnSignUp) {
			signUpNewAccoutn();
		} else if (event.getSource() == btnForgot) {
			forgotPassword();
		} else if (event.getSource() == btnPeek) {
			toggleShowPassword();
		}
	}

	private void validateLoginInfor() {
		// TODO Auto-generated method stub

	}

	private void signUpNewAccoutn() {
		// TODO Auto-generated method stub

	}

	private void forgotPassword() {
		// TODO Auto-generated method stub

	}

	private void toggleShowPassword() {
		// TODO Auto-generated method stub

	}

	@FXML
	void enterPressed(ActionEvent event) {

	}

	@FXML
	void passwordKeyReleased(KeyEvent event) {

	}
}