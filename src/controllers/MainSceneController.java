package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import model.EffortLog;
import model.LifeCycleStep;

public class MainSceneController {
	private String[] projectList;
	private String[] categoryList;
	private String[] planList;
	private String[] deliverableList;
	private String[] interuptionList;
	private String[] defectCateList;
	private LifeCycleStep[] stepList;
	private ArrayList<EffortLog> effortLogsList;
	private boolean isActive;
	private String startDateTime;
	private String stopDateTime;
	private int effortIDMax;

	@FXML
	public void initialize() {
		loadDef();
		initCombobox(cmbProjectEC, cmbCategoryEC, cmbDetailEC, cmbStepEC);
		initCombobox(cmbProjectEdit, cmbCategoryEdit, cmbDetailEdit, cmbStepEdit);
		initComboProject(cmbProjectDC);
		initComboProject(cmbProjectEL);
		initComboProject(cmbProjectDL);
		initCombobox(cmbProjectPK, cmbCategoryPK, cmbDetailPK, cmbStepPK);
		loadEffortLog("");
		initEffortTablePK();
		listEffortLogEdit(projectList[0]);
	}

	private void loadEffortLog(String project) {
		effortIDMax = -1;
		effortLogsList = new ArrayList<EffortLog>();
		String line = "";
		Scanner input = null;
		try {
			String filePath = new File("").getAbsolutePath();
			File file = new File(filePath + "/src/data/effortLogs.txt");
			input = new Scanner(file);
			while (input.hasNextLine()) {
				line = input.nextLine();
				String temp[] = line.split(";");
				String ID = temp[0].split(" ")[0];
				int id = Integer.parseInt(ID);
				if (effortIDMax < id) {
					effortIDMax = id;
				}
				String _date = temp[1].split(" ")[0];
				String _start = temp[1].split(" ")[1];
				String _stop = temp[2].split(" ")[1];
				String _project = temp[3];
				String _step = temp[4];
				String _cate = temp[5];
				String _detail = temp[6];
				EffortLog effort = new EffortLog(ID, _date, _start, _stop, _project,
						_step, _cate, _detail);
				if (project.equals("") || _project.equals(project))
					effortLogsList.add(effort);
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

	private void initEffortTablePK() {
		colIDPK.setCellValueFactory(new PropertyValueFactory<EffortLog, Integer>("ID"));
		colDatePK
				.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("date"));
		colStartPK.setCellValueFactory(
				new PropertyValueFactory<EffortLog, String>("startTime"));
		colStopPK.setCellValueFactory(
				new PropertyValueFactory<EffortLog, String>("stopTime"));
		colTimePK.setCellValueFactory(
				new PropertyValueFactory<EffortLog, String>("timeElapse"));
		colStepPK.setCellValueFactory(
				new PropertyValueFactory<EffortLog, String>("stepName"));
		colCatePK.setCellValueFactory(
				new PropertyValueFactory<EffortLog, String>("cateName"));
		colDetailPK.setCellValueFactory(
				new PropertyValueFactory<EffortLog, String>("detailName"));
		colSelectPK.setCellValueFactory(
				new PropertyValueFactory<EffortLog, String>("select"));
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
		isActive = false;

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

	private void initCombobox(ComboBox<String> cmbProject, ComboBox<String> cmbCategory,
			ComboBox<String> cmDdetail, ComboBox<String> cmbStep) {
		initComboProject(cmbProject);
		initCombo(0, cmbStep);
		cmbCategory.getItems().removeAll(cmbCategoryEC.getItems());
		cmbCategory.getItems().addAll(categoryList);
		cmbCategory.getSelectionModel().select(0);

		cmDdetail.getItems().removeAll(cmbDetailEC.getItems());
		cmDdetail.getItems().addAll(planList);
		cmDdetail.getSelectionModel().select(0);
	}

	private void initComboProject(ComboBox<String> cmbProject) {
		cmbProject.getItems().removeAll(cmbProjectEC.getItems());
		cmbProject.getItems().addAll(projectList);
		cmbProject.getSelectionModel().select(0);
	}

	@FXML
	void selectPorjectEC(ActionEvent event) {
		if (event.getSource() == cmbProjectEC) {
			int index = cmbProjectEC.getSelectionModel().getSelectedIndex();
			initCombo(index, cmbStepEC);
		} else if (event.getSource() == cmbStepEC) {
			changeCate(cmbStepEC, cmbCategoryEC);
		} else if (event.getSource() == cmbCategoryEC) {
			changeDetailCmb(cmbDetailEC, cmbCategoryEC, lblDetailEC);
		} else if (event.getSource() == cmbDetailEC) {
		}
	}

	@FXML
	void selectPorjectEdit(ActionEvent event) {
		if (event.getSource() == cmbProjectEdit) {
			String projectName = cmbProjectEdit.getSelectionModel().getSelectedItem()
					.toString();
			listEffortLogEdit(projectName);
		} else if (event.getSource() == cmbLogsEdit) {
			selectELtoEdit();
		}
	}

	private void listEffortLogEdit(String projectName) {
		ArrayList<String> effortLogs = new ArrayList<String>();
		String line = "";
		Scanner input = null;
		try {
			String filePath = new File("").getAbsolutePath();
			File file = new File(filePath + "/src/data/effortLogs.txt");
			input = new Scanner(file);
			while (input.hasNextLine()) {
				line = input.nextLine();
				String _project = line.split(";")[3];
				if (projectName.equals(_project))
					effortLogs.add(line);
			}
			cmbLogsEdit.getSelectionModel().select(null);
			cmbLogsEdit.getItems().removeAll(cmbLogsEdit.getItems());
			cmbLogsEdit.getItems().addAll(effortLogs);
			cmbLogsEdit.getSelectionModel().select(0);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	private void selectELtoEdit() {
		if (cmbLogsEdit.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		String log = cmbLogsEdit.getSelectionModel().getSelectedItem().toString();
		String selectID = log.split(";")[0];
		EffortLog temp = null;
		for (EffortLog curr : effortLogsList) {
			if (curr.getID().equals(selectID)) {
				temp = curr;
				break;
			}
		}
		txtDateEdit.setText(temp.getDate());
		txtStartEdit.setText(temp.getStartTime());
		txtStopEdit.setText(temp.getStopTime());
		initCombo(cmbProjectEdit.getSelectionModel().getSelectedIndex(), cmbStepEdit);
		cmbStepEdit.getSelectionModel().select(temp.getStepName());

		String cate = temp.getCateName();
		cmbCategoryEdit.getSelectionModel().select(cate);
		cmbDetailEdit.getItems().removeAll(cmbDetailEdit.getItems());
		if (cate.equals("Plans")) {
			cmbDetailEdit.getItems().addAll(planList);
		} else if (cate.equals("Deliverables")) {
			cmbDetailEdit.getItems().addAll(deliverableList);
		} else if (cate.equals("Defect")) {
			cmbDetailEdit.getItems().addAll(defectCateList);
		} else if (cate.equals("Interruptions")) {
			cmbDetailEdit.getItems().addAll(interuptionList);
		} else if (cate.equals("Others")) {
			cmbDetailEdit.getItems().add(temp.getDetailName());
		}
		cmbDetailEdit.getSelectionModel().select(temp.getDetailName());
	}

	@FXML
	void selectPorjectPK(ActionEvent event) {
		if (event.getSource() == cmbProjectPK) {
			int index = cmbProjectPK.getSelectionModel().getSelectedIndex();
			initCombo(index, cmbStepPK);
		} else if (event.getSource() == cmbStepPK) {
			changeCate(cmbStepPK, cmbCategoryPK);
		} else if (event.getSource() == cmbCategoryPK) {
			changeDetailCmb(cmbDetailPK, cmbCategoryPK, lblDetailPK);
		} else if (event.getSource() == cmbDetailPK) {
		} else if (event.getSource() == btnFilterPK) {
			searchEffortLogsPK();
		} else if (event.getSource() == btnCalculatePK) {
			double point = calculateStoryPoint();
			new Alert(Alert.AlertType.INFORMATION,
					"Story point estimated: " + String.format("%.2f", point),
					ButtonType.YES).showAndWait();
		}
	}

	private double calculateStoryPoint() {
		double sum = 0;
		int count = 0;
		ObservableList<EffortLog> list = tblEffortLogsPK.getItems();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				LocalTime time = LocalTime.parse(list.get(i).getTimeElapse(), formatter);
				double elapse = time.getHour() + time.getMinute() / 60.0
						+ time.getSecond() / 3600.0;
				sum += elapse;
				count++;
			}
		}
		return sum / count;

	}

	private void searchEffortLogsPK() {
		ArrayList<EffortLog> tempList = new ArrayList<EffortLog>();
		String searchProject = cmbProjectPK.getSelectionModel().getSelectedItem()
				.toString();
//		String searchStep = cmbStepPK.getSelectionModel().getSelectedItem().toString();
//		String searchCate = cmbCategoryPK.getSelectionModel().getSelectedItem()
//				.toString();
//		String searchDetail = cmbDetailPK.getSelectionModel().getSelectedItem()
//				.toString();
		for (EffortLog effortLog : effortLogsList) {
			if (effortLog.getProjectName().equals(searchProject)) {
				tempList.add(effortLog);
			}
		}
		tblEffortLogsPK.getItems().removeAll(tblEffortLogsPK.getItems());
		tblEffortLogsPK.getItems().addAll(tempList);
	}

	private void initCombo(int index, ComboBox<String> cmbStep) {
		cmbStep.getItems().removeAll(cmbStep.getItems());
		for (LifeCycleStep step : stepList) {
			if (step != null && step.getProjectID() == index + 1) {
				cmbStep.getItems().add(step.getName());
			}
		}
		cmbStep.getSelectionModel().select(0);
	}

	private void changeCate(ComboBox<String> cmbStep, ComboBox<String> cmbCate) {
		String name = cmbStep.getSelectionModel().getSelectedItem();
		for (LifeCycleStep step : stepList) {
			if (step != null && step.getName().equals(name)) {
				cmbCate.getSelectionModel().select(step.getEffortCateID() - 1);
				return;
			}
		}
	}

	private void changeDetailCmb(ComboBox<String> cmbDetail, ComboBox<String> cmbCate,
			Label lblDetail) {
		cmbDetail.getItems().removeAll(cmbDetail.getItems());
		int index = cmbCate.getSelectionModel().getSelectedIndex();
		String detail = cmbCate.getSelectionModel().getSelectedItem().toString();
		lblDetail.setText(detail + ":");
		switch (index) {
		case 0: {
			cmbDetail.getItems().addAll(planList);
			break;
		}
		case 1: {
			cmbDetail.getItems().addAll(deliverableList);
			break;
		}
		case 2: {
			cmbDetail.getItems().addAll(interuptionList);
			break;
		}
		case 3: {
			cmbDetail.getItems().addAll(defectCateList);
			break;
		}
		case 4: {
			TextInputDialog inputdialog = new TextInputDialog("Enter the detail here");
			inputdialog.setContentText("Detail: ");
			inputdialog.setHeaderText("Input Detail");
			inputdialog.showAndWait();
			String other = inputdialog.getEditor().getText();
			cmbDetail.getItems().removeAll(cmbDetailEC.getItems());
			cmbDetail.getItems().add(other);
			break;
		}
		default:
			cmbDetail.getItems().addAll(planList);
			break;
		}
		cmbDetail.getSelectionModel().select(0);
	}

	@FXML
	void btnHandlerEC(ActionEvent event) {
		if (event.getSource() == btnStartEC) {
			startActivity();
		} else if (event.getSource() == btnStopEC) {
			stopActivity();
		} else if (event.getSource() == btnEditEC) {
			tabMain.getSelectionModel().select(tabEditEL);
		} else if (event.getSource() == btnDefectEC) {
			tabMain.getSelectionModel().select(tabDefect);
		} else if (event.getSource() == btnDefEC) {
			tabMain.getSelectionModel().select(tabDef);
		} else if (event.getSource() == btnLogsEC) {
			tabMain.getSelectionModel().select(tabEL);
		}
	}

	private void startActivity() {
		if (isActive) {
			new Alert(Alert.AlertType.ERROR, "The clock is already running!")
					.showAndWait();
		} else {
			isActive = true;
			lblClockEC.setStyle("-fx-background-color: #00FF00 ");
			lblClockEC.setText("Clock is running!");
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter
					.ofPattern("MM-dd-yyyy HH:mm:ss");
			startDateTime = myDateObj.format(myFormatObj);
			lblStatusEC.setText("This activity started at: " + startDateTime);
			lblStatusEC.setTextFill(Color.GREEN);
			lblStatusEC.setVisible(true);
		}

	}

	private void stopActivity() {
		if (!isActive) {
			new Alert(Alert.AlertType.ERROR, "The clock is not running!").showAndWait();
			return;
		}
		isActive = false;
		lblClockEC.setStyle("-fx-background-color: #FF0000 ");
		lblClockEC.setText("Clock is stopped!");
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter
				.ofPattern("MM-dd-yyyy HH:mm:ss");
		stopDateTime = myDateObj.format(myFormatObj);
		String effortLog = "";
		effortIDMax++;

		String date = startDateTime.split(" ")[0];
		String startTime = startDateTime.split(" ")[1];
		String stopTime = stopDateTime.split(" ")[1];
		String projectName = cmbProjectEC.getSelectionModel().getSelectedItem()
				.toString();
		String step = cmbStepEC.getSelectionModel().getSelectedItem().toString();
		String category = cmbCategoryEC.getSelectionModel().getSelectedItem().toString();
		String detail = cmbDetailEC.getSelectionModel().getSelectedItem().toString();

		effortLog += effortIDMax + ";";
		effortLog += startDateTime + ";";
		effortLog += stopDateTime + ";";
		effortLog += projectName + ";";
		effortLog += step + ";";
		effortLog += category + ";";
		effortLog += detail + "\n";

		FileWriter writer = null;
		try {
			lblStatusEC.setText("This activity stopped at: " + stopDateTime);
			lblStatusEC.setTextFill(Color.GREEN);
			lblStatusEC.setVisible(true);
			String filePath = new File("").getAbsolutePath();
			File file = new File(filePath + "/src/data/effortLogs.txt");
			writer = new FileWriter(file, true);
			writer.write(effortLog);
			writer.close();
			EffortLog newEL = new EffortLog(effortIDMax + "", date, startTime, stopTime,
					projectName, step, category, detail);
			effortLogsList.add(newEL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	private TabPane tabMain;
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
	private Label lblEditSave;
	@FXML
	private Label lblDetailEC;
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
	private TableView<EffortLog> tblEffortLogsPK;
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
	@FXML
	private ComboBox<String> cmbProjectPK;
	@FXML
	private ComboBox<String> cmbStepPK;
	@FXML
	private ComboBox<String> cmbCategoryPK;
	@FXML
	private ComboBox<String> cmbDetailPK;
	@FXML
	private Label lblDetailPK;
	@FXML
	private TextField txtSearchPK;
	@FXML
	private Button btnFilterPK;
	@FXML
	private Button btnCalculatePK;
	@FXML
	private TableColumn<EffortLog, String> colCatePK;
	@FXML
	private TableColumn<EffortLog, String> colDatePK;
	@FXML
	private TableColumn<EffortLog, String> colDetailPK;
	@FXML
	private TableColumn<EffortLog, Integer> colIDPK;
	@FXML
	private TableColumn<EffortLog, String> colSelectPK;
	@FXML
	private TableColumn<EffortLog, String> colStartPK;
	@FXML
	private TableColumn<EffortLog, String> colStepPK;
	@FXML
	private TableColumn<EffortLog, String> colStopPK;
	@FXML
	private TableColumn<EffortLog, String> colTimePK;
}
