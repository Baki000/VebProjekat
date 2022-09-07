package beans;

import java.time.LocalDate;

import enums.FeeStatus;
import formats.DateFormat;

public class Fee {

	private int id;
	private String feeType;
	private LocalDate payDay;
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private double price;
	private UserCommon customer;
	private String status;
	private int entries;
	
	
	public Fee() {
		super();
		
	}


	public Fee(int id) {
		super();
		this.id = id;
	}


	public Fee(int id, String feeType, LocalDate payDay, LocalDate dateStart, LocalDate dateEnd, double price,
			UserCommon customer, String status, int entries) {
		super();
		this.id = id;
		this.feeType = feeType;
		this.payDay = payDay;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.price = price;
		this.customer = customer;
		this.status = status;
		this.entries = entries;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFeeType() {
		return feeType;
	}


	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}


	public String getPayDay() {
		return DateFormat.dateToString(payDay);
	}


	public void setPayDay(String payDay) {
		if(payDay == null || payDay.equals("")) {
			return;
		}
		this.payDay = DateFormat.stringToDate(payDay);
	}


	public String getDateStart() {
		return DateFormat.dateToString(dateStart);
	}


	public void setDateStart(String dateStart) {
		if(dateStart == null || dateStart.equals("")) {
			return;
		}
		this.dateStart = DateFormat.stringToDate(dateStart);
	}


	public String getDateEnd() {
		return DateFormat.dateToString(dateEnd);
	}


	public void setDateEnd(String dateEnd) {
		if(dateEnd == null || dateEnd.equals("")) {
			return;
		}
		this.dateEnd = DateFormat.stringToDate(dateEnd);
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public UserCommon getCustomer() {
		return customer;
	}


	public void setCustomer(UserCommon customer) {
		this.customer = customer;
	}


	public String getStatus() {
		return status;
	}



	public int getEntries() {
		return entries;
	}


	public void setEntries(int entries) {
		this.entries = entries;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String convertToString() {
		return id + ";" + feeType + ";" + DateFormat.dateToString(payDay) + ";" + DateFormat.dateToString(dateStart) + ";" + DateFormat.dateToString(dateEnd) + ";" + price + ";" 
				+ customer.getId() + ";" + status + ";" + entries;
	}
	
	
	
	
	
}
