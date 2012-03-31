package classes.relations;

public class Charge {	
	private int groupId;
	private int workerId;
	private int activityId;
	private String chargeTime;
	
	

	public Charge(int groupId, int workerId, int activityId, String chargeTime) {
		super();
		this.groupId = groupId;
		this.workerId = workerId;
		this.activityId = activityId;
		this.chargeTime = chargeTime;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}
	
	

}
