package classes.entity;


public class Supplier {

	private int supplierId;
	private String origanization;//µ¥Î»Ãû³Æ
	private String address;
	private String contactTel;
	
	
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Supplier(int supplierId, String origanization) {
		super();
		this.supplierId = supplierId;
		this.origanization = origanization;
	}


	public Supplier(int supplierId, String origanization, String address,
			String contactTel) {
		super();
		this.supplierId = supplierId;
		this.origanization = origanization;
		this.address = address;
		this.contactTel = contactTel;
	}


	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getOriganization() {
		return origanization;
	}
	public void setOriganization(String origanization) {
		this.origanization = origanization;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	
	public String toString(){
		return this.getSupplierId()+this.getOriganization()
				+this.getAddress()+this.getContactTel();
	}
	
}
