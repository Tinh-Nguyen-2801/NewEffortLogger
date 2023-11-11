package controllers;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.LifeCycleStep;

public class MainSceneController {
	private String[] projectList;
	private String[] categoryList;
	private String[] planList;
	private String[] deliverableList;
	private String[] interuptionList;
	private String[] defectCateList;
	private LifeCycleStep[] stepList;

	@FXML
	public void initialize() {
		loadDef();
		initCombobox();
	}

	/**
	 * Load the definition of the program
	 */
	private void loadDef() {
		projectList = new String[10];
		categoryList = new String[5];
		planList = new String[10];
		deliverableList = new String[10];
		interuptionList = new String[10];
		defectCateList = new String[15];
		stepList = new LifeCycleStep[50];

		String line = "";
		Scanner input = null;
		try {
			String filePath = new File("").getAbsolutePath();
			File file = new File(filePath + "/src/data/ELDefinition.txt");
			input = new Scanner(file);

			// Project List
			line = input.nextLine();
			String[] temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				projectList[i] = temp[i].substring(temp[i].indexOf(',') + 1);
			}

			// Category List
			line = input.nextLine();
			temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				categoryList[i] = temp[i].substring(temp[i].indexOf(',') + 1);
			}

			// Plan List
			line = input.nextLine();
			temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				planList[i] = temp[i].substring(temp[i].indexOf(',') + 1);
			}
			// Deliverable List
			line = input.nextLine();
			temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				deliverableList[i] = temp[i].substring(temp[i].indexOf(',') + 1);
			}
			// Interruption List
			line = input.nextLine();
			temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				interuptionList[i] = temp[i].substring(temp[i].indexOf(',') + 1);
			}
			// Defect List
			line = input.nextLine();
			temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				defectCateList[i] = temp[i].substring(temp[i].indexOf(',') + 1);
			}
			int i = 0;
			while (input.hasNextLine()) {
				line = input.nextLine();
				temp = line.split(",");
				stepList[i] = new LifeCycleStep(Integer.parseInt(temp[0]), temp[1],
						Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
						Integer.parseInt(temp[4]));
				i++;
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

	private void initCombobox() {
		cmbProjectEC.getItems().removeAll(cmbProjectEC.getItems());
		cmbProjectEC.getItems().addAll(projectList);
		cmbProjectEC.getSelectionModel().select(0);

		cmbCategoryEC.getItems().removeAll(cmbCategoryEC.getItems());
		cmbCategoryEC.getItems().addAll(categoryList);
		initComboEC(0);
	}

	@FXML
	void selectPorjectEC(ActionEvent event) {
		if (event.getSource() == cmbProjectEC) {
			int index = cmbProjectEC.getSelectionModel().getSelectedIndex();
			initComboEC(index);
		} else if (event.getSource() == cmbStepEC) {
			changeCateEC();
		} else if (event.getSource() == cmbCategoryEC) {
		} else if (event.getSource() == cmbDetailEC) {
		}
	}

	private void initComboEC(int index) {
		cmbStepEC.getItems().removeAll(cmbStepEC.getItems());
		for (LifeCycleStep step : stepList) {
			if (step != null && step.getProjectID() == index + 1) {
				cmbStepEC.getItems().add(step.getName());
			}
		}
		cmbStepEC.getSelectionModel().select(0);
	}

	private void changeCateEC() {
		String name = cmbStepEC.getSelectionModel().getSelectedItem();
		for (LifeCycleStep step : stepList) {
			if (step != null && step.getName().equals(name)) {
				cmbCategoryEC.getSelectionModel().select(step.getEffortCateID() - 1);
				return;
			}
		}
	}

	@FXML
	void clearEffortLog(ActionEvent event) {

	}

	@FXML
	void backToECPage(ActionEvent event) {
		tabEC.getContent().requestFocus();
	}

	@FXML
	private ComboBox<String> cmbCateDC;

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
	private ComboBox<String> cmbRemoveDC;

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
	private ComboBox<String> cmbCategoryEC;

	@FXML
	private ComboBox<String> cmbCategoryEdit;

	@FXML
	private ComboBox<String> cmbDetailEC;

	@FXML
	private ComboBox<String> cmbDetailEdit;

	@FXML
	private ComboBox<String> cmbEntryDC;

	@FXML
	private ComboBox<String> cmbFixDC;

	@FXML
	private ComboBox<String> cmbInjectDC;

	@FXML
	private ComboBox<String> cmbLogsEdit;

	@FXML
	private ComboBox<String> cmbProjectDC;

	@FXML
	private ComboBox<String> cmbProjectDL;

	@FXML
	private ComboBox<String> cmbProjectEC;

	@FXML
	private ComboBox<String> cmbProjectEL;

	@FXML
	private ComboBox<String> cmbProjectEdit;

	@FXML
	private ComboBox<String> cmbStepEC;

	@FXML
	private ComboBox<String> cmbStepEdit;

	@FXML
	private Label lblClockEC;

	@FXML
	private Label lblCounterDC;

	@FXML
	private Label lblDefEC;

	@FXML
	private Label lblDefD;

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
