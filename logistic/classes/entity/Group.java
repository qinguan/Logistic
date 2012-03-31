package classes.entity;

import java.sql.Time;
import java.util.ArrayList;

public class Group {

	private int groupId;
	private String groupName;
	private String groupDuty;
	private String office;
	private String workTime;
	// private Time workStartTime;
	// private Time workEndTime;

	private ArrayList<Activity> activities;
	private ArrayList<Worker> workers;

	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Group(int groupId, String groupName) { super(); this.groupId =
	 * groupId; this.groupName = groupName; }
	 */

	public Group(int groupId, String groupName, String groupDuty,
			String office, String workTime) {
		super();
		this.groupId = groupId;
		if (groupName == null) {
			this.groupName = "No Name";
		} else {
			this.groupName = groupName;
		}

		if (groupDuty == null) {
			this.groupDuty = "";
		} else {
			this.groupDuty = groupDuty;
		}

		if (office == null) {
			this.office = "";
		} else {
			this.office = office;
		}

		if (workTime == null) {
			this.workTime = null;
		} else {
			this.workTime = workTime;
		}

	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDuty() {
		return groupDuty;
	}

	public void setGroupDuty(String groupDuty) {
		this.groupDuty = groupDuty;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public ArrayList<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(ArrayList<Worker> workers) {
		this.workers = workers;
	}

	public String toString() {
		return this.getGroupId() + this.getGroupName() + this.getGroupDuty()
				+ this.getOffice() + this.getWorkTime();
	}

}
