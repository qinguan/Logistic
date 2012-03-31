package classes.entity;

public class Stock {

	private int stockId;
	private String stockPlace;
	private String stockTel;
	private String stockArea;
	
	
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/*public Stock(int stockId, String stockPlace) {
		super();
		this.stockId = stockId;
		this.stockPlace = stockPlace;
	}*/



	public Stock(int stockId, String stockPlace, String stockTel,
			String stockArea) {
		super();
		this.stockId = stockId;
		
		if (stockPlace == null) {
			this.stockPlace = "";
		}
		else {
			this.stockPlace = stockPlace;
		}
		
		if (stockTel == null) {
			this.stockTel ="";
		}
		else {
			this.stockTel = stockTel;
		}
		
		if (stockArea == null) {
			this.stockArea ="";
		}
		else {
			this.stockArea = stockArea;
		}
		
	}


	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getStockPlace() {
		return stockPlace;
	}
	public void setStockPlace(String stockPlace) {
		this.stockPlace = stockPlace;
	}
	public String getStockTel() {
		return stockTel;
	}
	public void setStockTel(String stockTel) {
		this.stockTel = stockTel;
	}
	public String getStockArea() {
		return stockArea;
	}
	public void setStockArea(String stockArea) {
		this.stockArea = stockArea;
	}
	
	public String toString(){
		return this.getStockId()+this.getStockPlace()
				+this.getStockTel()+this.getStockArea();
	}
	
	
}
