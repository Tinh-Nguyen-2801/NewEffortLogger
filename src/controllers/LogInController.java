package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.Scanner;

import helper.GeneralHelper;

import java.io.File;

public class LogInController {
	private boolean showPass = false;
	@FXML
	private Hyperlink lblForgot;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnPeek;

	@FXML
	private Hyperlink lblSignUp;

	@FXML
	private ImageView imgPeek;

	@FXML
	private Label lblWarning;
	@FXML
	private Label lblStatus;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtUsername;

	@FXML
	void btnPressed(ActionEvent event) {
		if (event.getSource() == btnLogin) {
			validateLoginInfo();
		} else if (event.getSource() == lblSignUp) {
			signUpNewAccoutn();
		} else if (event.getSource() == lblForgot) {
			goToRecovery();
		} else if (event.getSource() == btnPeek) {
			toggleShowPassword();
		}
	}

	/**
	 * Validate the login username and password
	 * 
	 * @param input string
	 * @return byte array of the string
	 */
	private void validateLoginInfo() {
		String username = txtUsername.getText();
		String password = txtPassword.getText();

		if (username.equals("")) {
			lblStatus.setText("Enter username!");
			lblStatus.setVisible(true);
			return;
		}
		if (password.equals("")) {
			lblStatus.setText("Enter password!");
			lblStatus.setVisible(true);
			return;
		}
		String line = "";
		Scanner input = null;
		try {
			String filePath = new File("").getAbsolutePath();
			File file = new File(filePath + "/src/data/accountList.txt");
			input = new Scanner(file);
			boolean usernameFound = false;
			while (input.hasNextLine() && !usernameFound) {
				line = input.nextLine();
				if (line.contains(username)) {
					if (username.equals(line.split(",")[0])) {
						usernameFound = true;
						String storedPass = line.split(",")[1];
						GeneralHelper hp = new GeneralHelper();
						if (hp.generalHelp(password).equals(storedPass)) {
							grantAccess(line.split(",")[2]);
						} else {
							lblStatus.setText("Incorrect Password!");
							lblStatus.setVisible(true);
						}
					}
				}
			}
			if (!usernameFound) {
				lblStatus.setText("Account not found!");
				lblStatus.setVisible(true);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	/**
	 * Open the Sign Up Window
	 */
	private void signUpNewAccoutn() {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/ui/SignUpScreen.fxml"));
			Scene scene = new Scene(root);
			Stage effortLoggerStage = new Stage();
			effortLoggerStage.setScene(scene);
			effortLoggerStage.setTitle("Signing Up");
			effortLoggerStage.show();
			final Stage stage = (Stage) lblSignUp.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the Forgot Password Windows
	 */
	private void goToRecovery() {
		// TODO Auto-generated method stub
		System.out.println("To do: Forgot password!");

	}

	/**
	 * Toggle the peak button to show/hide password
	 */
	private void toggleShowPassword() {
		showPass = !showPass;
		txtPassword.setVisible(showPass);
	}

	/**
	 * Grant the acess to the user
	 * 
	 * @param employeeId
	 */
	private void grantAccess(String employeeId) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ui/MainScene.fxml"));
			Scene scene = new Scene(root);
			Stage signUpStage = new Stage();
			signUpStage.setScene(scene);
			signUpStage.setTitle("Effort Logger");
			signUpStage.show();
			final Stage currStage = (Stage) btnLogin.getScene().getWindow();
			currStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void enterPressed(ActionEvent event) {
		if (event.getSource() == txtPassword || event.getSource() == txtUsername) {
			validateLoginInfo();
		}
	}

	@FXML
	void passwordKeyReleased(KeyEvent event) {

	}
}
