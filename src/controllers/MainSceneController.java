package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainSceneController {
	@FXML
	void clearEffortLog(ActionEvent event) {

	}

	@FXML
	void backToECPage(ActionEvent event) {
		tabEC.getContent().requestFocus();
	}

	@FXML
	private ComboBox<?> btnCateDC;

	@FXML
	private Button btnClearDC;

	@FXML
	private Button btnClearEdit;

	@FXML
	private Button btnCreateDC;

	@FXML
	private Button btnDefEC;

	@FXML
	private Button btnDefectEC;

	@FXML
	private Button btnDeleteDC;

	@FXML
	private Button btnDeleteEdit;

	@FXML
	private Button btnECDC;

	@FXML
	private Button btnEditEC;

	@FXML
	private Button btnLogsEC;

	@FXML
	private Button btnOpenDC;

	@FXML
	private ComboBox<?> btnRemoveDC;

	@FXML
	private Button btnSplitEdit;

	@FXML
	private Button btnStartEC;

	@FXML
	private Button btnStopEC;

	@FXML
	private Button btnUpdateDC;

	@FXML
	private Button btnUpdateEdit;

	@FXML
	private ComboBox<?> cmbCategoryEC;

	@FXML
	private ComboBox<?> cmbCategoryEdit;

	@FXML
	private ComboBox<?> cmbDetailEC;

	@FXML
	private ComboBox<?> cmbDetailEdit;

	@FXML
	private ComboBox<?> cmbEntryDC;

	@FXML
	private ComboBox<?> cmbFixDC;

	@FXML
	private ComboBox<?> cmbInjectDC;

	@FXML
	private ComboBox<?> cmbLogsEdit;

	@FXML
	private ComboBox<?> cmbProjectDC;

	@FXML
	private ComboBox<?> cmbProjectDL;

	@FXML
	private ComboBox<?> cmbProjectEC;

	@FXML
	private ComboBox<?> cmbProjectEL;

	@FXML
	private ComboBox<?> cmbProjectEdit;

	@FXML
	private ComboBox<?> cmbStepEC;

	@FXML
	private ComboBox<?> cmbStepEdit;

	@FXML
	private Label lblClockEC;

	@FXML
	private Label lblCounterDC;

	@FXML
	private Label lblCounterEdit;

	@FXML
	private Label lblStatusDC;

	@FXML
	private Label lblStatusEC;

	@FXML
	private Tab tabCycle;

	@FXML
	private Tab tabDL;

	@FXML
	private Tab tabDef;

	@FXML
	private Tab tabDefect;

	@FXML
	private Tab tabDefectCate;

	@FXML
	private Tab tabDeli;

	@FXML
	private Tab tabEC;

	@FXML
	private Tab tabEL;

	@FXML
	private Tab tabEditEL;

	@FXML
	private Tab tabInterrupt;

	@FXML
	private Tab tabPlan;

	@FXML
	private Tab tabProject;

	@FXML
	private TableView<?> tblDefectLog;

	@FXML
	private TableView<?> tblEffortLog;

	@FXML
	private TextField txtDateEdit;

	@FXML
	private Button txtECEdit;

	@FXML
	private TextField txtNameDC;

	@FXML
	private TextField txtNumDC;

	@FXML
	private TextField txtStartEdit;

	@FXML
	private TextField txtStopEdit;

	@FXML
	private TextArea txtSymptomDC;

}
