package model;

public class LifeCycleStep {
	private int ID;
	private String name;
	private int projectID;
	private int effortCateID;
	private int deliverableID;

	public LifeCycleStep(int _ID, String _name, int _projectID, int _effortID,
			int _deliverID) {
		ID = _ID;
		name = _name;
		projectID = _projectID;
		effortCateID = _effortID;
		deliverableID = _deliverID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getEffortCateID() {
		return effortCateID;
	}

	public void setEffortCateID(int effortCateID) {
		this.effortCateID = effortCateID;
	}

	public int getDeliverableID() {
		return deliverableID;
	}

	public void setDeliverableID(int deliverableID) {
		this.deliverableID = deliverableID;
	}
}
