package classes.relations;

public class Offer {
	private int supplierId;
	private int materialId;
	private int offerNum;
	
	public Offer(int supplierId , int materialId , int offerNum){
		this.supplierId=supplierId;
		this.materialId=materialId;
		this.offerNum = offerNum;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getOfferNum() {
		return offerNum;
	}

	public void setOfferNum(int offerNum) {
		this.offerNum = offerNum;
	}
	
	
	


}
