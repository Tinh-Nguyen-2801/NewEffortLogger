package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.CheckBox;

public class EffortLog {
	private String ID;
	private String date;
	private String startTime;
	private String stopTime;
	private String timeElapse;
	private String projectName;
	private String stepName;
	private String cateName;
	private String detailName;
	private CheckBox select;

	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}

	public EffortLog(String _ID, String _date, String start, String stop, String project,
			String step, String cate, String detail) {
		ID = _ID;
		date = _date;
		startTime = start;
		stopTime = stop;
		projectName = project;
		stepName = step;
		cateName = cate;
		detailName = detail;
		timeElapse = calculateElapsedTime(start, stop);
		select = new CheckBox();
	}

	public boolean isSelected() {
		return select.isSelected();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getTimeElapse() {
		return timeElapse;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public static String calculateElapsedTime(String startTimeString,
			String endTimeString) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date startTime = null;
		Date endTime = null;

		try {
			startTime = format.parse(startTimeString);
			endTime = format.parse(endTimeString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long millisecondDifference = endTime.getTime() - startTime.getTime();
		long seconds = millisecondDifference / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;

		long remainingSeconds = seconds % 60;
		long remainingMinutes = minutes % 60;

		return String.format("%02d:%02d:%02d", hours, remainingMinutes, remainingSeconds);
	}
}
