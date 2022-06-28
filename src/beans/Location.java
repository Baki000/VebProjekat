package beans;

public class Location {
<<<<<<< HEAD
	private String longitudeLength;
	private String latitudeWidth;
	private Address address;
	
=======
	private int id;
	private String street;
	private String city;
	private int postalCode;
	private String longitudeLength;
	private String latitudeWidth;

>>>>>>> d15706ed4e385a8183ed073cee91171707281a9b
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
<<<<<<< HEAD
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
=======

	public Location(int id, String streetAndNumber, String city, int postalCode, String longitudeLength,
			String latitudeWidth) {
		super();
		this.id = id;
		this.street = streetAndNumber;
		this.city = city;
		this.postalCode = postalCode;
		this.longitudeLength = longitudeLength;
		this.latitudeWidth = latitudeWidth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String streetAndNumber) {
		this.street = streetAndNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
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

	
>>>>>>> d15706ed4e385a8183ed073cee91171707281a9b
}
