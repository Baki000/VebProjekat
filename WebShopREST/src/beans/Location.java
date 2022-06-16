package beans;

public class Location {
	private String longitudeLength;
	private String latitudeWidth;
	private Address address;
	
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(String longitudeLength, String latitudeWidth, Address address) {
		super();
		this.longitudeLength = longitudeLength;
		this.latitudeWidth = latitudeWidth;
		this.address = address;
	}
	public String getLongitudeLength() {
		return longitudeLength;
	}
	public void setLongitudeLength(String longitudeLength) {
		this.longitudeLength = longitudeLength;
	}
	public String getLatitudeWidth() {
		return latitudeWidth;
	}
	public void setLatitudeWidth(String latitudeWidth) {
		this.latitudeWidth = latitudeWidth;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
