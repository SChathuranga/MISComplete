package entities;

import java.sql.Date;


public class Students {

	private String RegNo;
	private String FullName;
	private String Address;
	private Date DOB;
	private String NIC;
	private String Sex;
	private int ContactNo;
	private Date RegDate;
	private String FathersName;
	private String NameofPrev;
	private String Nationality;
	private String Religion;
	private int GradeEntered;
	private String ClassEntered;
	private String OtherDetails;
	private byte[] ProfilePicture;
	private int CurrentGrade;
	private String CurrentClass;
	
	public String getRegNo() {
		return RegNo;
	}
	public void setRegNo(String regNo) {
		RegNo = regNo;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public java.util.Date getDOB() {
		return DOB;
	}
	public void setDOB(Date date) {
		DOB = date;
	}
	public String getNIC() {
		return NIC;
	}
	public void setNIC(String nIC) {
		NIC = nIC;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getContactNo() {
		return ContactNo;
	}
	public void setContactNo(int contactNo) {
		ContactNo = contactNo;
	}
	public java.util.Date getRegDate() {
		return RegDate;
	}
	public void setRegDate(Date date) {
		RegDate = date;
	}
	public String getFathersName() {
		return FathersName;
	}
	public void setFathersName(String fathersName) {
		FathersName = fathersName;
	}
	public String getNameofPrev() {
		return NameofPrev;
	}
	public void setNameofPrev(String nameofPrev) {
		NameofPrev = nameofPrev;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getReligion() {
		return Religion;
	}
	public void setReligion(String religion) {
		Religion = religion;
	}
	public int getGradeEntered() {
		return GradeEntered;
	}
	public void setGradeEntered(int gradeEntered) {
		GradeEntered = gradeEntered;
	}
	public String getClassEntered() {
		return ClassEntered;
	}
	public void setClassEntered(String classEntered) {
		ClassEntered = classEntered;
	}
	public String getOtherDetails() {
		return OtherDetails;
	}
	public void setOtherDetails(String otherDetails) {
		OtherDetails = otherDetails;
	}
	public byte[] getProfilePicture() {
		return ProfilePicture;
	}
	public void setProfilePicture(byte[] profilePicture) {
		ProfilePicture = profilePicture;
	}
	public int getCurrentGrade() {
		return CurrentGrade;
	}
	public void setCurrentGrade(int currentGrade) {
		CurrentGrade = currentGrade;
	}
	public String getCurrentClass() {
		return CurrentClass;
	}
	public void setCurrentClass(String currentClass) {
		CurrentClass = currentClass;
	}
	
	public Students(String regNo, String fullName, String address, Date dOB,
			String nIC, String sex, int contactNo, Date regDate,
			String fathersName, String nameofPrev, String nationality,
			String religion, int gradeEntered, String classEntered,
			String otherDetails, byte[] profilePicture, int currentGrade,
			String currentClass) {
		super();
		RegNo = regNo;
		FullName = fullName;
		Address = address;
		DOB = dOB;
		NIC = nIC;
		Sex = sex;
		ContactNo = contactNo;
		RegDate = regDate;
		FathersName = fathersName;
		NameofPrev = nameofPrev;
		Nationality = nationality;
		Religion = religion;
		GradeEntered = gradeEntered;
		ClassEntered = classEntered;
		OtherDetails = otherDetails;
		ProfilePicture = profilePicture;
		CurrentGrade = currentGrade;
		CurrentClass = currentClass;
	}
	public Students() {
		super();
	}
	
	
}
