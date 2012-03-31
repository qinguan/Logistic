package classes.relations;

public class Used {
	private int workerId;
	private int materialId;
	private int materialUsedNum;
	
	
	public Used(int workerId, int materialId, int materialUsedNum) {
		super();
		this.workerId = workerId;
		this.materialId = materialId;
		this.materialUsedNum = materialUsedNum;
	}
	
	public int getWorkerId() {
		return workerId;
	}
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public int getMaterialUsedNum() {
		return materialUsedNum;
	}
	public void setMaterialUsedNum(int materialUsedNum) {
		this.materialUsedNum = materialUsedNum;
	}
	
	
	
	
	
}
