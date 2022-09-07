package beans;

public class CustomerType {
	
	private int id;
	private String typeName;
	private double discount;
	private double pointsNeeded;
	
	public CustomerType() {
		super();
		
	}
	
	public CustomerType(int id) {
		super();
		this.id = id;
	}
	
	public CustomerType(int id, String typeName, double discount, double pointsNeeded) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.discount = discount;
		this.pointsNeeded = pointsNeeded;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPointsNeeded() {
		return pointsNeeded;
	}
	public void setPointsNeeded(double pointsNeeded) {
		this.pointsNeeded = pointsNeeded;
	}
	
	
}
