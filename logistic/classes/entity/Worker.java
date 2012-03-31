package classes.entity;


public class Worker {
	
	private int workerId;
	private String workerName;
	private String workerTel;
	private String workerSex;
	
	
	public Worker() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Worker(int workerId, String workerName) {
		super();
		this.workerId = workerId;
		this.workerName = workerName;
	}


	public Worker(int workerId, String workerName, String workerTel,
			String workerSex) {
		super();
		this.workerId = workerId;
		this.workerName = workerName;
		this.workerTel = workerTel;
		this.workerSex = workerSex;
	}


	public int getWorkerId() {
		return workerId;
	}
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerTel() {
		return workerTel;
	}
	public void setWorkerTel(String workerTel) {
		this.workerTel = workerTel;
	}
	public String getWorkerSex() {
		return workerSex;
	}
	public void setWorkerSex(String workerSex) {
		this.workerSex = workerSex;
	}
	
	public String toString(){
		return this.getWorkerId()+this.getWorkerName()
				+this.getWorkerTel()+this.getWorkerSex();
	}
}
