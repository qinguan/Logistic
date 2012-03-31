package classes.relations;

public class Deposit {
	private int materialId;
	private int stockId;
	private int depositNum;
	
	public Deposit(int materialId,int stockId, int depositNum){
		this.materialId=materialId;
		this.stockId = stockId;
		this.depositNum = depositNum;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public int getDepositNum() {
		return depositNum;
	}

	public void setDepositNum(int depositNum) {
		this.depositNum = depositNum;
	}
	

	
	
	
}
