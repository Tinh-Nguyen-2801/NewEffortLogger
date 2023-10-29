package controllers;

import java.awt.image.DataBufferDouble;
import java.io.File;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.StatusMessage;

public class SignUpController {

	@FXML
	private Button btnPeek;

	@FXML
	private Button btnPeek1;

	@FXML
	private Button btnSignUp;

	@FXML
	private DatePicker dateDOB;

	@FXML
	private ImageView imgPeek;

	@FXML
	private ImageView imgPeek1;

	@FXML
	private Hyperlink lblForgot;

	@FXML
	private Hyperlink lblLogIn;

	@FXML
	private Label lblSttID;

	@FXML
	private Label lblSttDOB;

	@FXML
	private Label lblSttFirst;

	@FXML
	private Label lblSttLast;

	@FXML
	private Label lblSttPass;

	@FXML
	private Label lblSttPassCf;

	@FXML
	private Label lblSttUser;

	@FXML
	private Label lblWarning;

	@FXML
	private TextField txtEmployeeID;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private PasswordField txtPasswordCfm;

	@FXML
	private TextField txtUsername;

	@FXML
	void btnPressed(ActionEvent event) {
		if (event.getSource() == btnSignUp) {
			validateSignUpInfo();
		} else if (event.getSource() == lblLogIn) {
			backToLoginScreen();
		} else if (event.getSource() == lblForgot) {
			goToRecovery();
		} else if (event.getSource() == btnPeek) {
			toggleShowPassword();
		}
	}

	private void validateSignUpInfo() {
		boolean statusUser = validateUsername();
		boolean statusPass = validatePassword();
		boolean statusInfo = validateUserInfo();
		if (statusUser && statusPass && statusInfo) {
			// TODO: sign up for new user
		}
	}

	private boolean validateUsername() {
		String username = txtUsername.getText().trim();
		String strPattern = "^[a-z0-9_.]{6,16}$";
		boolean valid = false;
		if (username.equals("")) {
			lblSttUser.setText("Required Field");
		} else if (username.length() < 6 || username.length() > 16) {
			lblSttUser.setText("Length must be from 6 to 16");
		} else if (username.charAt(0) < 'a' || username.charAt(0) > 'z') {
			lblSttUser.setText("Must start with a lower case letter!");
		} else if (!username.matches(strPattern)) {
			lblSttUser.setText("Contains lower case letter, digit, _ and .");
		} else {
			String line = "";
			Scanner input = null;
			try {
				String filePath = new File("").getAbsolutePath();
				File file = new File(filePath + "/src/data/temporary.txt");
				input = new Scanner(file);
				boolean userFound = false;
				while (input.hasNextLine() && !userFound) {
					line = input.nextLine();
					if (line.contains(username)) {
						lblSttUser.setText("Duplicated Username");
						userFound = true;
					}
				}
				if (!userFound) {
					lblSttUser.setText("Valid Username!");
					valid = true;
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
		lblSttUser.setTextFill(valid ? Color.GREEN : Color.RED);
		lblSttUser.setVisible(true);
		return valid;
	}

	private boolean validatePassword() {
		String pass = txtPassword.getText().trim();
		String passCf = txtPassword.getText().trim();
		String passPattern = "^[a-zA-Z0-9_.!?]{6,16}$";
		boolean result = false;
		if (pass.equals("")) {
			lblSttPass.setText("Required Field!");
		} else if (pass.length() < 6 || pass.length() > 16) {
			lblSttPass.setText("Password Length: 6 to 16");
		} else if (!pass.matches(passPattern)) {
			lblSttPass.setText("Contains: alphabet letters, digits, _, ., !, or ?");
		} else if (!passCf.equals(pass)) {
			lblSttPassCf.setText("Confirmed password does not match!");
		} else {
			result = true;
			lblSttPass.setText("Valid Password!");
			lblSttPassCf.setText("Password matches!");
		}
		lblSttPass.setVisible(true);
		lblSttPassCf.setVisible(true);
		if (result) {
			lblSttPass.setTextFill(Color.GREEN);
			lblSttPassCf.setTextFill(Color.GREEN);
		}
		return result;
	}

	private boolean validateUserInfo() {
		boolean result = false;
		String firstName = txtFirstName.getText().trim();
		String lastName = txtLastName.getText().trim();
		String DOB = dateDOB.getValue().toString();
		int year = dateDOB.getValue().getYear();
		String employeeID = txtEmployeeID.getText().trim();
		String namePattern = "^[a-zA-Z]$";
		String IDPattern = "^[0-9]$";
		if (firstName.equals("")) {
			lblSttFirst.setText("Required Field!");
		} else if (!firstName.matches(namePattern)) {
			lblSttFirst.setText("Invalid First Name");
		} else if (lastName.equals("")) {
			lblSttLast.setText("Required Field!");
		} else if (!lastName.matches(namePattern)) {
			lblSttLast.setText("Invalid Last Name");
		} else if (year > 2005) {
			lblSttDOB.setText("Invalid Year!");
		} else if (employeeID.equals("")) {
			lblSttID.setText("Required Field!");
		} else if (employeeID.matches(IDPattern)) {
			lblSttID.setText("Invalid ID");
		} else {
			String line = "";
			Scanner input = null;
			try {
				String filePath = new File("").getAbsolutePath();
				File file = new File(filePath + "/src/data/employeeList.txt");
				input = new Scanner(file);
				boolean IDFound = false;
				while (input.hasNextLine() && !IDFound) {
					line = input.nextLine();
					if (line.contains(employeeID)) {
						String[] info = line.split(",");
						if (employeeID.equals(info[0]) && firstName.equals(info[1]) && lastName.equals(info[2])
								&& DOB.equals(info[3])) {
							IDFound = true;
							result = true;
						}
					}
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

		return result;
	}

	private void backToLoginScreen() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ui/LoginScreen.fxml"));
			Scene scene = new Scene(root);
			Stage effortLoggerStage = new Stage();
			effortLoggerStage.setScene(scene);
			effortLoggerStage.setTitle("Effort Logger Login");
			effortLoggerStage.show();
			final Stage stage = (Stage) lblLogIn.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void goToRecovery() {
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
