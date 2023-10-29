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
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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
			validateLoginInfor();
		} else if (event.getSource() == lblSignUp) {
			signUpNewAccoutn();
		} else if (event.getSource() == lblForgot) {
			goToRecovery();
		} else if (event.getSource() == btnPeek) {
			toggleShowPassword();
		}
	}

	private void validateLoginInfor() {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String line = "";
		Scanner input = null;
		try {
			String filePath = new File("").getAbsolutePath();
			File file = new File(filePath + "/src/data/temporary.txt");
			input = new Scanner(file);
			boolean usernameFound = false;
			while (input.hasNextLine() && !usernameFound) {
				line = input.nextLine();
				if (line.contains(username)) {
					usernameFound = true;
					String storedPass = line.split(",")[1];
					if (toHexString(getSHA(password)).equals(storedPass)) {
						grantAccess();
					} else {
						lblStatus.setText("Incorrect Password!");
						lblStatus.setVisible(true);
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

	// MessageDigest instance for hashing using SHA256
	private byte[] getSHA(String input) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	private String toHexString(byte[] hash) {
		// Convert byte array of hash into digest
		BigInteger number = new BigInteger(1, hash);

		// Convert the digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

	private void signUpNewAccoutn() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ui/SignUpScreen.fxml"));
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

	private void goToRecovery() {
		// TODO Auto-generated method stub
		System.out.println("To do: Forgot password!");

	}

	private void toggleShowPassword() {
		showPass = !showPass;
		txtPassword.setVisible(showPass);
	}

	private void grantAccess() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ui/MainScene.fxml"));
			Scene scene = new Scene(root);
			Stage effortLoggerStage = new Stage();
			effortLoggerStage.setScene(scene);
			effortLoggerStage.setTitle("Effort Logger Login");
			effortLoggerStage.show();
			final Stage stage = (Stage) btnLogin.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void enterPressed(ActionEvent event) {

	}

	@FXML
	void passwordKeyReleased(KeyEvent event) {

	}
}
