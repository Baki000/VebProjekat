package beans;

public class CustomerType {
	private String typeName;
	private int discount;
	private int pointsNeeded;
	
	public CustomerType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerType(String typeName, int discount, int pointsNeeded) {
		super();
		this.typeName = typeName;
		this.discount = discount;
		this.pointsNeeded = pointsNeeded;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getPointsNeeded() {
		return pointsNeeded;
	}
	public void setPointsNeeded(int pointsNeeded) {
		this.pointsNeeded = pointsNeeded;
	}
	
	
}
