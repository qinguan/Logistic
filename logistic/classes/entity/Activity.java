package classes.entity;

import java.sql.Time;

public class Activity {

	private int activityId;
	private String activityName;
	private String activityPlace;
	private String activityTime;
//	private Time activityStartTime;
//	private Time activityEndTime;
	private String activityContact;
	private int activityPersonNum;
	
		
	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * public Activity(int activityId, String activityName, String activityPlace) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityPlace = activityPlace;
	}
*/

	public Activity(int activityId, String activityName, String activityPlace,
			String activityTime,String activityContact, int activityPersonNum) {
		super();
		this.activityId = activityId;
		if(activityName == null){
			this.activityName = "";
		}
		else {
			this.activityName = activityName;
		}
		
		if(activityPlace == null){
			this.activityPlace ="";
		}
		else {
			this.activityPlace = activityPlace;
		}
		
		if(activityTime == null){
			this.activityTime =null;
		}
		else {
			this.activityTime = activityTime;
		}
		
		if(activityContact == null){
			this.activityContact = "";
		}
		else {
			this.activityContact = activityContact;
		}
		
		if(activityPersonNum < 0){
			this.activityPersonNum = -1;
		}
		else {
			this.activityPersonNum = activityPersonNum;
		}
		
	}



	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityPlace() {
		return activityPlace;
	}
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public String getActivityContact() {
		return activityContact;
	}
	public void setActivityContact(String activityContact) {
		this.activityContact = activityContact;
	}
	public int getActivityPersonNum() {
		return activityPersonNum;
	}
	public void setActivityPersonNum(int activityPersonNum) {
		this.activityPersonNum = activityPersonNum;
	}
	
	public String toString(){
		return this.getActivityId()+this.getActivityName()
		+this.getActivityPlace()+this.getActivityTime()
		+this.getActivityContact()+this.getActivityPersonNum();
	} 
	
}
