package classes.entity;


public class Material {

	private int materialId;
	private String materialName;
	private String materialUsed;
	
	
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/*public Material(int materialId, String materialName) {
		super();
		this.materialId = materialId;
		this.materialName = materialName;
	}
*/

	public Material(int materialId, String materialName, String materialUsed) {
		super();
		this.materialId = materialId;
		
		if (materialName == null) {
			this.materialName = "";
		}
		else {
			this.materialName = materialName;
		}
		
		if (materialUsed == null) {
			this.materialUsed ="";
		}
		else {
			this.materialUsed = materialUsed;
		}
		
	}


	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialUsed() {
		return materialUsed;
	}
	public void setMaterialUsed(String materialUsed) {
		this.materialUsed = materialUsed;
	}
	
	public String toString(){
		return this.getMaterialId()+this.getMaterialName()+this.getMaterialUsed();
	}
	
}
