package entities;

import java.sql.Date;

public class Books {

	private String ISBN;
	private String AccNo;
	private String Bname;
	private String Author;
	private String Publisher;
	private int PublishedYear;
	private String Edition;
	private String Price;
	private String Status;
	private String MemID;
	private Date IssuedDate;
	private Date DueDate;
	private Date ReturnDate;
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getAccNo() {
		return AccNo;
	}
	public void setAccNo(String accNo) {
		AccNo = accNo;
	}
	public String getBname() {
		return Bname;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public int getPublishedYear() {
		return PublishedYear;
	}
	public void setPublishedYear(int publishedYear) {
		PublishedYear = publishedYear;
	}
	public String getEdition() {
		return Edition;
	}
	public void setEdition(String edition) {
		Edition = edition;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getMemID() {
		return MemID;
	}
	public void setMemID(String memID) {
		MemID = memID;
	}
	public Date getIssuedDate() {
		return IssuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		IssuedDate = issuedDate;
	}
	public Date getDueDate() {
		return DueDate;
	}
	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}
	
	public Date getReturnDate() {
		return ReturnDate;
	}
	public void setReturnDate(Date returnDate) {
		ReturnDate = returnDate;
	}
	
	public Books(String iSBN, String accNo, String bname, String author,
			String publisher, int publishedYear, String edition, String price,
			String status, String memID, Date issuedDate, Date dueDate, Date returnDate) {
		super();
		ISBN = iSBN;
		AccNo = accNo;
		Bname = bname;
		Author = author;
		Publisher = publisher;
		PublishedYear = publishedYear;
		Edition = edition;
		Price = price;
		Status = status;
		MemID = memID;
		IssuedDate = issuedDate;
		DueDate = dueDate;
		ReturnDate = returnDate;
	}
	public Books() {
		super();
	}
	
}
